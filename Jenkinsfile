pipeline {
    agent any

    environment {
        REPO_URL = 'https://github.com/younesen/titreminexcel.git'
        REPO_NAME = 'younesen/titreminexcel'        // owner/repo
        BACKEND_IMAGE = 'younesen/titreminexcel-backend'
        FRONTEND_IMAGE = 'younesen/titreminexcel-frontend'
        ARGO_APP = 'titreminexcel'
        ARGO_SERVER = 'https://192.168.245.238:8081'
        // Note: credentials will be injected via withCredentials blocks
    }

    stages {

        stage('Checkout') {
            steps {
                // checkout branch main
                git branch: 'main', url: "${env.REPO_URL}"
            }
        }

        stage('Backend Tests') {
            steps {
                dir('titreminexcel') {
                    bat 'mvn -B -q test'
                }
            }
        }

        stage('Compute new tag') {
            steps {
                script {
                    // get latest tag (if none => v0.0.0)
                    def lastTagRaw = bat(script: 'git describe --tags --abbrev=0 || echo v0.0.0', returnStdout: true).trim()
                    // sanitize CR/LF
                    lastTagRaw = lastTagRaw.tokenize()[0]
                    echo "Last tag: ${lastTagRaw}"

                    // simple semver patch increment (vMAJOR.MINOR.PATCH)
                    def parts = lastTagRaw.replaceFirst(/^v/, '').split('\\.')
                    if (parts.size() < 3) {
                        parts = ['0','0','0']
                    }
                    def major = parts[0].toInteger()
                    def minor = parts[1].toInteger()
                    def patch = parts[2].toInteger() + 1
                    def newTag = "v${major}.${minor}.${patch}"

                    // expose to env
                    env.NEW_TAG = newTag
                    echo "New tag will be: ${env.NEW_TAG}"
                }
            }
        }

        stage('Build & Tag Docker images') {
            steps {
                // build backend
                dir('titreminexcel') {
                    bat "docker build -t ${BACKEND_IMAGE}:latest -t ${BACKEND_IMAGE}:${env.NEW_TAG} ."
                }
                // build frontend
                dir('frontend') {
                    bat "docker build -t ${FRONTEND_IMAGE}:latest -t ${FRONTEND_IMAGE}:${env.NEW_TAG} ."
                }
            }
        }

        stage('Push images (Docker Hub)') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat """
                        echo Logging into Docker registry...
                        echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin

                        docker push ${BACKEND_IMAGE}:${env.NEW_TAG}
                        docker push ${FRONTEND_IMAGE}:${env.NEW_TAG}

                        docker logout
                    """
                }
            }
        }

        stage('Update Helm values & commit') {
            steps {
                dir('helm-charts/titreminexcel') {
                    withCredentials([string(credentialsId: 'github-token', variable: 'GITHUB_TOKEN')]) {
                        bat """
                            echo Updating values.yaml with tagged images...
                            powershell -Command "(Get-Content values.yaml) -replace '$(Get-Content values.yaml | Select-String -Pattern \"${BACKEND_IMAGE}:[^\\r\\n]*\" -AllMatches).Matches.Value', '${BACKEND_IMAGE}:${env.NEW_TAG}' | Set-Content values.yaml"
                            powershell -Command "(Get-Content values.yaml) -replace '$(Get-Content values.yaml | Select-String -Pattern \"${FRONTEND_IMAGE}:[^\\r\\n]*\" -AllMatches).Matches.Value', '${FRONTEND_IMAGE}:${env.NEW_TAG}' | Set-Content values.yaml"

                            REM show updated snippet
                            type values.yaml | findstr /I "image:"

                            REM configure git user (so commit is not rejected)
                            git config user.email "jenkins@ci.local"
                            git config user.name "jenkins-ci"

                            REM create a commit and push back using token
                            git add values.yaml
                            git commit -m "ci: bump images to ${env.NEW_TAG} [ci skip]" || echo "no changes to commit"

                            REM push using token in HTTPS remote url (avoid exposing the token in logs)
                            setlocal enabledelayedexpansion
                            set PUSH_URL=https://%GITHUB_TOKEN%@github.com/${REPO_NAME}.git
                            git push !PUSH_URL! HEAD:main
                            endlocal
                        """
                    }
                }
            }
        }

        stage('Create Git Tag & GitHub Release') {
            steps {
                withCredentials([string(credentialsId: 'github-token', variable: 'GITHUB_TOKEN')]) {
                    script {
                        bat """
                            REM create annotated git tag and push
                            git tag -a ${env.NEW_TAG} -m "Release ${env.NEW_TAG}"
                            setlocal enabledelayedexpansion
                            set PUSH_URL=https://%GITHUB_TOKEN%@github.com/${REPO_NAME}.git
                            git push !PUSH_URL! refs/tags/${env.NEW_TAG}
                            endlocal
                        """

                        // Generate a simple changelog (git log between last and new tag)
                        def changelog = bat(script: "git --no-pager log --pretty=format:%s %n%b -n 50 ${env.NEW_TAG} --reverse", returnStdout: true).trim()
                        if (!changelog) { changelog = "Release ${env.NEW_TAG}" }
                        // call GitHub Releases API
                        bat """
                            curl -s -X POST -H "Authorization: token %GITHUB_TOKEN%" -H "Content-Type: application/json" ^
                                -d "{ \\"tag_name\\": \\"${env.NEW_TAG}\\", \\"name\\": \\"${env.NEW_TAG}\\", \\"body\\": \\"${changelog.replaceAll('\"','\\\\\\\"')}\\" }" ^
                                https://api.github.com/repos/${REPO_NAME}/releases
                        """
                    }
                }
            }
        }

        stage('Trigger ArgoCD sync') {
            steps {
                withCredentials([string(credentialsId: 'argocd-token', variable: 'ARGO_TOKEN')]) {
                    bat """
                        echo Triggering ArgoCD sync for ${ARGO_APP}...
                        curl -k -s -X POST "%ARGO_SERVER%/api/v1/applications/${ARGO_APP}/sync" ^
                            -H "Authorization: Bearer %ARGO_TOKEN%" ^
                            -H "Content-Type: application/json" ^
                            -d "{ \\"revision\\": \\"${env.NEW_TAG}\\" }"
                        echo Done.
                    """
                }
            }
        }

    } // stages

    post {
        success {
            echo "✅ Pipeline finished successfully — images pushed and ArgoCD sync triggered for ${env.NEW_TAG}"
        }
        failure {
            echo "❌ Pipeline failed — check logs"
            // Optional: send email / slack here (excluded per your request)
        }
    }
}
