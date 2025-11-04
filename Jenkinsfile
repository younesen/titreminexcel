pipeline {
agent any

```
environment {
    BACKEND_IMAGE = 'younesen/titreminexcel-backend'
    FRONTEND_IMAGE = 'younesen/titreminexcel-frontend'
    ARGO_APP = 'titreminexcel'
    ARGO_SERVER = 'https://192.168.245.238:8081'
    VERSION_FILE = 'version.txt'
}

stages {

    stage('Checkout') {
        steps {
            git branch: 'main', url: 'https://github.com/younesen/titreminexcel.git'
        }
    }

    // 🧮 Étape 1 : Calcul du nouveau tag
    stage('Determine Version Tag') {
        steps {
            script {
                // Lire la version actuelle ou créer v1.0.0 si première fois
                def currentVersion = fileExists(env.VERSION_FILE) ? readFile(env.VERSION_FILE).trim() : "v1.0.0"
                def (major, minor, patch) = currentVersion.replace("v", "").tokenize('.')
                def newVersion = "v${major}.${minor}.${(patch.toInteger() + 1)}"
                echo "🆕 Nouvelle version : ${newVersion}"

                // Sauvegarder pour les prochaines étapes
                writeFile file: env.VERSION_FILE, text: newVersion
                env.APP_VERSION = newVersion
            }
        }
    }

    // 🧪 Étape 2 : Tests du backend
    stage('Backend Tests') {
        steps {
            dir('titreminexcel') {
                bat 'mvn clean test'
            }
        }
    }

    // 🏗️ Étape 3 : Build Docker du backend et frontend avec tag versionné
    stage('Build Docker Images') {
        steps {
            parallel (
                "Backend": {
                    dir('titreminexcel') {
                        bat "docker build -t %BACKEND_IMAGE%:%APP_VERSION% ."
                    }
                },
                "Frontend": {
                    dir('frontend') {
                        bat "docker build -t %FRONTEND_IMAGE%:%APP_VERSION% ."
                    }
                }
            )
        }
    }

    // 🚢 Étape 4 : Push Docker Hub (avec tag versionné)
    stage('Push Docker Images') {
        steps {
            withCredentials([usernamePassword(
                credentialsId: 'dockerhub-creds',
                usernameVariable: 'DOCKER_USER',
                passwordVariable: 'DOCKER_PASS'
            )]) {
                bat """
                    echo 🔐 Connexion à Docker Hub...
                    echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
                    docker push %BACKEND_IMAGE%:%APP_VERSION%
                    docker push %FRONTEND_IMAGE%:%APP_VERSION%
                    docker logout
                """
            }
        }
    }

    // ⚙️ Étape 5 : Mise à jour du chart Helm avec le tag versionné
    stage('Update Helm Chart') {
        steps {
            dir('helm-charts/titreminexcel') {
                bat """
                    echo 📝 Mise à jour des images avec la version %APP_VERSION%...
                    powershell -Command "(Get-Content values.yaml) -replace 'younesen/titreminexcel-backend:.*', 'younesen/titreminexcel-backend:%APP_VERSION%' | Set-Content values.yaml"
                    powershell -Command "(Get-Content values.yaml) -replace 'younesen/titreminexcel-frontend:.*', 'younesen/titreminexcel-frontend:%APP_VERSION%' | Set-Content values.yaml"
                """
            }
        }
    }

    // 💾 Étape 6 : Commit et push vers GitHub (déclenche ArgoCD)
    stage('Commit & Push Changes') {
        steps {
            withCredentials([usernamePassword(
                credentialsId: 'github-creds',
                usernameVariable: 'GIT_USER',
                passwordVariable: 'GIT_PASS'
            )]) {
                bat """
                    git config user.email "jenkins@ci.com"
                    git config user.name "Jenkins CI"
                    git add helm-charts/titreminexcel/values.yaml version.txt
                    git commit -m "🚀 Release %APP_VERSION%"
                    git push https://%GIT_USER%:%GIT_PASS%@github.com/younesen/titreminexcel.git main
                """
            }
        }
    }

    // 🚀 Étape 7 : Déploiement via ArgoCD (optionnel car auto via GitOps)
    stage('Deploy via ArgoCD') {
        steps {
            withCredentials([string(credentialsId: 'argocd-token', variable: 'ARGO_TOKEN')]) {
                bat """
                    echo 🔑 Déploiement manuel ArgoCD pour %APP_VERSION%...
                    curl -k -X POST "%ARGO_SERVER%/api/v1/applications/%ARGO_APP%/sync" ^
                        -H "Authorization: Bearer %ARGO_TOKEN%" ^
                        -H "Content-Type: application/json" ^
                        -d "{\\\"revision\\\": \\\"main\\\"}"
                """
            }
        }
    }

    // 🧾 Étape 8 : Génération du changelog GitHub
    stage('Generate GitHub Release') {
        steps {
            withCredentials([string(credentialsId: 'github-token', variable: 'GITHUB_TOKEN')]) {
                bat """
                    echo 🏷️ Création de la release %APP_VERSION% sur GitHub...
                    curl -X POST https://api.github.com/repos/younesen/titreminexcel/releases ^
                        -H "Authorization: token %GITHUB_TOKEN%" ^
                        -d "{\\"tag_name\\": \\"%APP_VERSION%\\", \\"name\\": \\"Release %APP_VERSION%\\", \\"body\\": \\"Auto release via Jenkins GitOps\\"}"
                """
            }
        }
    }
}

post {
    success {
        echo "✅ Déploiement versionné réussi : %APP_VERSION%"
    }
    failure {
        echo '❌ Échec du pipeline - rollback automatique...'
        script {
            try {
                withCredentials([string(credentialsId: 'argocd-token', variable: 'ARGO_TOKEN')]) {
                    bat """
                        echo 🔁 Rollback vers la dernière version stable...
                        curl -k -X POST "%ARGO_SERVER%/api/v1/applications/%ARGO_APP%/rollback" ^
                            -H "Authorization: Bearer %ARGO_TOKEN%" ^
                            -H "Content-Type: application/json" ^
                            -d "{\\\"revision\\\": \\\"previous\\\"}"
                    """
                }
                echo '✅ Rollback exécuté avec succès !'
            } catch (err) {
                echo '⚠️ Rollback ArgoCD échoué.'
            }
        }
    }
}
```

}
