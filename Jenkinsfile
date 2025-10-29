pipeline {
    agent any

    environment {
        BACKEND_IMAGE = 'younesen/titreminexcel-backend'
        FRONTEND_IMAGE = 'younesen/titreminexcel-frontend'
        ARGO_APP = 'titreminexcel'
        ARGO_SERVER = 'https://192.168.245.238:8081'
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

        stage('Update Helm Chart') {
            steps {
                dir('helm-charts/titreminexcel') {
                    bat """
                        echo Mise à jour du fichier values.yaml...
                        powershell -Command "(Get-Content values.yaml) -replace 'younesen/titreminexcel-backend:.*', 'younesen/titreminexcel-backend:latest' | Set-Content values.yaml"
                        powershell -Command "(Get-Content values.yaml) -replace 'younesen/titreminexcel-frontend:.*', 'younesen/titreminexcel-frontend:latest' | Set-Content values.yaml"
                    """
                }
            }
        }

        stage('Deploy via ArgoCD') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'argocd-creds',
                    usernameVariable: 'ARGO_USER',
                    passwordVariable: 'ARGO_PASS'
                )]) {
                    script {
                        // Méthode 1 : Utilisation de l'API REST avec curl (recommandée)
                        bat """
                            echo Synchronisation de l'application %ARGO_APP%...
                            curl -k -X POST "%ARGO_SERVER%/api/v1/applications/%ARGO_APP%/sync" ^
                                -H "Content-Type: application/json" ^
                                -u "%ARGO_USER%:%ARGO_PASS%" ^
                                -d "{\\\"revision\\\": \\\"main\\\"}"

                            echo.
                            echo Attente du déploiement (30 secondes)...
                            timeout /t 30 /nobreak

                            echo Vérification du statut...
                            curl -k -s "%ARGO_SERVER%/api/v1/applications/%ARGO_APP%" ^
                                -u "%ARGO_USER%:%ARGO_PASS%" | findstr "health status"
                        """
                    }
                }
            }
        }
    }

    post {
        success {
            echo '✅ Build, Push et Déploiement ArgoCD réussis !'
        }
        failure {
            echo '❌ Erreur - Vérifie les logs Jenkins.'
        }
    }
}