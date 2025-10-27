pipeline {
    agent any

    environment {
        DOCKERHUB_USERNAME = 'younesen'
        DOCKERHUB_PASSWORD = 'younes123EN@'
        GITHUB_USERNAME = 'younesen'
        GITHUB_TOKEN = 'votre_token_github'
        BACKEND_IMAGE = 'younesen/titreminexcel-backend'
        FRONTEND_IMAGE = 'younesen/titreminexcel-frontend'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/younesen/titreminexcel.git'
            }
        }

        stage('Build Backend') {
            steps {
                dir('titreminexcel/backend') {
                    bat 'docker build -t %BACKEND_IMAGE%:latest .'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('titreminexcel/frontend') {
                    bat 'docker build -t %FRONTEND_IMAGE%:latest .'
                }
            }
        }

        stage('Push Images') {
            steps {
                bat '''
                    echo Connexion à Docker Hub...
                    echo %DOCKERHUB_PASSWORD% | docker login -u %DOCKERHUB_USERNAME% --password-stdin
                    echo Push de l image backend...
                    docker push %BACKEND_IMAGE%:latest
                    echo Push de l image frontend...
                    docker push %FRONTEND_IMAGE%:latest
                    docker logout
                '''
            }
        }

        stage('Update Helm values') {
            steps {
                bat '''
                    echo === Mise à jour de values.yaml ===

                    REM Met à jour les images dans values.yaml
                    powershell -Command "(Get-Content helm-charts/titreminexcel/values.yaml) -replace 'repository:.*titreminexcel-backend', 'repository: %BACKEND_IMAGE%' | Set-Content helm-charts/titreminexcel/values.yaml"
                    powershell -Command "(Get-Content helm-charts/titreminexcel/values.yaml) -replace 'repository:.*titreminexcel-frontend', 'repository: %FRONTEND_IMAGE%' | Set-Content helm-charts/titreminexcel/values.yaml"

                    echo === Vérification des modifications ===
                    git diff

                    git config user.email "jenkins@ci.com"
                    git config user.name "jenkins"

                    REM Configure et pousse les changements
                    git remote set-url origin https://%GITHUB_USERNAME%:%GITHUB_TOKEN%@github.com/younesen/titreminexcel.git

                    if not defined GIT_DIFF git diff --quiet (
                        echo ✅ Aucun changement nécessaire
                    ) else (
                        echo 🔄 Commit des modifications...
                        git add helm-charts/titreminexcel/values.yaml
                        git commit -m "CI: Update image tags to latest"
                        git push origin main
                        echo ✅ Changements poussés avec succès
                    )
                '''
            }
        }
    }

    post {
        always {
            echo '🏁 Pipeline terminé'
        }
        success {
            echo '✅ Succès - Build et déploiement terminés'
        }
        failure {
            echo '❌ Échec - Vérifiez les logs'
        }
    }
}