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

        // 🧪 Étape 1 : Tests du backend Spring Boot
        stage('Backend Tests') {
            steps {
                dir('titreminexcel') {
                    bat """
                        echo 🚀 Lancement des tests Maven...
                        mvn clean test
                    """
                }
            }
        }

        // 🏗️ Étape 2 : Build Docker du backend
        stage('Build Backend') {
            steps {
                dir('titreminexcel') {
                    bat 'docker build -t %BACKEND_IMAGE%:latest .'
                }
            }
        }

        // 🧱 Étape 3 : Build Docker du frontend
        stage('Build Frontend') {
            steps {
                dir('frontend') {
                    bat 'docker build -t %FRONTEND_IMAGE%:latest .'
                }
            }
        }

        // 🚢 Étape 4 : Push Docker Hub
        stage('Push Images') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    bat """
                        echo 🔐 Connexion à Docker Hub...
                        echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
                        docker push %BACKEND_IMAGE%:latest
                        docker push %FRONTEND_IMAGE%:latest
                        docker logout
                    """
                }
            }
        }

        // ⚙️ Étape 5 : Mise à jour du chart Helm
        stage('Update Helm Chart') {
            steps {
                dir('helm-charts/titreminexcel') {
                    bat """
                        echo 📝 Mise à jour du fichier values.yaml...
                        powershell -Command "(Get-Content values.yaml) -replace 'younesen/titreminexcel-backend:.*', 'younesen/titreminexcel-backend:latest' | Set-Content values.yaml"
                        powershell -Command "(Get-Content values.yaml) -replace 'younesen/titreminexcel-frontend:.*', 'younesen/titreminexcel-frontend:latest' | Set-Content values.yaml"

                        echo ✅ values.yaml mis à jour :
                        type values.yaml | findstr "image:"
                    """
                }
            }
        }

        // 🚀 Étape 6 : Déploiement via ArgoCD (avec Token API)
        stage('Deploy via ArgoCD') {
            steps {
                withCredentials([string(credentialsId: 'argocd-token', variable: 'ARGO_TOKEN')]) {
                    bat """
                        echo 🔑 Déploiement via ArgoCD API avec token...

                        echo 🚀 Synchronisation de l'application %ARGO_APP%...
                        curl -k -X POST "%ARGO_SERVER%/api/v1/applications/%ARGO_APP%/sync" ^
                            -H "Authorization: Bearer %ARGO_TOKEN%" ^
                            -H "Content-Type: application/json" ^
                            -d "{\\\"revision\\\": \\\"main\\\"}"

                        echo ⏳ Attente du déploiement...
                        ping -n 30 127.0.0.1 > nul

                        echo 📊 Vérification du statut final :
                        curl -k -s "%ARGO_SERVER%/api/v1/applications/%ARGO_APP%" ^
                            -H "Authorization: Bearer %ARGO_TOKEN%"
                    """
                }
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline complet réussi : tests, build, push et déploiement ArgoCD !'
        }
        failure {
            echo '❌ Échec du pipeline - tentative de rollback ArgoCD...'
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
                    echo '⚠️ Échec du rollback ArgoCD.'
                }
            }
        }
    }
}
