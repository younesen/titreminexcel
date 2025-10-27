pipeline {
    agent any

    environment {
        BACKEND_IMAGE = 'younesen/titreminexcel-backend'
        FRONTEND_IMAGE = 'younesen/titreminexcel-frontend'
        GIT_CREDENTIALS = 'github-creds'  // 🔐 ID Jenkins pour ton compte GitHub
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/younesen/titreminexcel.git'
            }
        }

        stage('Build Backend') {
            steps {
                dir('titreminexcel') {
                    bat 'docker build -t %BACKEND_IMAGE%:latest .'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontend') {
                    bat 'docker build -t %FRONTEND_IMAGE%:latest .'
                }
            }
        }

        stage('Push Images') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    bat """
                        echo Connexion à Docker Hub...
                        echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
                        docker push %BACKEND_IMAGE%:latest
                        docker push %FRONTEND_IMAGE%:latest
                        docker logout
                    """
                }
            }
        }

        // 🌍 Nouvelle étape : déploiement via ArgoCD
        stage('Deploy via ArgoCD') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'github-creds',
                    usernameVariable: 'GIT_USER',
                    passwordVariable: 'GIT_PASS'
                )]) {
                    dir('helm-chart') {
                        bat """
                            git clone https://%GIT_USER%:%GIT_PASS%@github.com/younesen/titreminexcel-helm.git
                            cd titreminexcel-helm
                            powershell -Command "(Get-Content values.yaml) -replace 'younesen/titreminexcel-backend:.*', 'younesen/titreminexcel-backend:latest' | Set-Content values.yaml"
                            powershell -Command "(Get-Content values.yaml) -replace 'younesen/titreminexcel-frontend:.*', 'younesen/titreminexcel-frontend:latest' | Set-Content values.yaml"
                            git config user.email "jenkins@ci.com"
                            git config user.name "Jenkins CI"
                            git add values.yaml
                            git commit -m "Update Helm chart images"
                            git push origin main
                        """
                    }
                }
            }
        }
    }

    post {
        success {
            echo '✅ Build, Push & Déploiement via ArgoCD terminés avec succès !'
        }
        failure {
            echo '❌ Échec - vérifie les logs Jenkins'
        }
    }
}  // ← CETTE ACCOLADE FERMANTE ÉTAIT MANQUANTE !