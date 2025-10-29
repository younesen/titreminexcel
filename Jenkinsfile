pipeline {
    agent any

    environment {
        BACKEND_IMAGE = 'younesen/titreminexcel-backend'
        FRONTEND_IMAGE = 'younesen/titreminexcel-frontend'
        ARGO_APP = 'titreminexcel'
        ARGO_SERVER = 'https://192.168.245.238:8081'
        ARGO_PATH = 'C:\\Program Files\\argocd.exe'
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
            bat """
                echo "Démarrage du port-forward en arrière-plan..."
                start /B wsl kubectl port-forward -n argocd svc/argocd-server 8081:443 --address 0.0.0.0
                timeout /t 5
                echo Connexion à ArgoCD...
                wsl argocd login localhost:8081 --username %ARGO_USER% --password %ARGO_PASS% --insecure
                echo Lancement du déploiement...
                wsl argocd app sync %ARGO_APP% --grpc-web
                echo Vérification du statut...
                wsl argocd app wait %ARGO_APP% --timeout 180 --health --sync
                echo "Arrêt du port-forward..."
                wsl pkill -f "kubectl port-forward"
            """
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
