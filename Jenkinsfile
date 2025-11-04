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

        // 🏷️ Étape 2 : Détermination du tag de version
        stage('Define Version Tag') {
            steps {
                script {
                    def dateTag = new Date().format("yyyyMMdd-HHmm")
                    env.VERSION_TAG = "v1.0-${dateTag}"
                    echo "🆕 Nouvelle version : ${env.VERSION_TAG}"
                }
            }
        }

        // 🏗️ Étape 3 : Build Docker du backend
        stage('Build Backend') {
            steps {
                dir('titreminexcel') {
                    bat "docker build -t %BACKEND_IMAGE%:%VERSION_TAG% ."
                }
            }
        }

        // 🧱 Étape 4 : Build Docker du frontend
        stage('Build Frontend') {
            steps {
                dir('frontend') {
                    bat "docker build -t %FRONTEND_IMAGE%:%VERSION_TAG% ."
                }
            }
        }

        // 🚢 Étape 5 : Push Docker Hub
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
                        docker push %BACKEND_IMAGE%:%VERSION_TAG%
                        docker push %FRONTEND_IMAGE%:%VERSION_TAG%
                        docker logout
                    """
                }
            }
        }

        // ⚙️ Étape 6 : Mise à jour du chart Helm
        stage('Update Helm Chart') {
            steps {
                dir('helm-charts/titreminexcel') {
                    bat '''
                        echo 🧩 Mise à jour du fichier values.yaml avec la version ${VERSION_TAG}...
                        powershell -Command "(Get-Content values.yaml) -replace 'younesen/titreminexcel-backend:.*', 'younesen/titreminexcel-backend:${VERSION_TAG}' | Set-Content values.yaml" || exit /b 0
                        powershell -Command "(Get-Content values.yaml) -replace 'younesen/titreminexcel-frontend:.*', 'younesen/titreminexcel-frontend:${VERSION_TAG}' | Set-Content values.yaml" || exit /b 0
                        echo ✅ values.yaml mis à jour :
                        type values.yaml | findstr "image:" || exit /b 0
                    '''
                }
            }
        }


        stage('Push Helm Update to GitHub') {
            steps {
                dir('helm-charts/titreminexcel') {
                    withCredentials([usernamePassword(credentialsId: 'github-creds', passwordVariable: 'GIT_PASS', usernameVariable: 'GIT_USER')]) {
                        bat '''
                            git config user.name "jenkins"
                            git config user.email "jenkins@local"

                            echo 🌀 Récupération des dernières modifications distantes...
                            git pull https://%GIT_USER%:%GIT_PASS%@github.com/younesen/titreminexcel.git main --rebase

                            echo 🧩 Commit des modifications Helm...
                            git add values.yaml
                            git commit -m "Update image tags to ${VERSION_TAG}" || echo "Aucun changement à valider"

                            echo 🚀 Push vers GitHub...
                            git push https://%GIT_USER%:%GIT_PASS%@github.com/younesen/titreminexcel.git main
                        '''
                    }
                }
            }
        }


        // 🚀 Étape 7 : Déploiement via ArgoCD
        stage('Deploy via ArgoCD') {
            steps {
                withCredentials([string(credentialsId: 'argocd-token', variable: 'ARGO_TOKEN')]) {
                    bat """
                        echo 🔑 Déploiement de la version ${VERSION_TAG} via ArgoCD API...

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
            echo "✅ Pipeline terminé avec succès ! Version déployée : ${env.VERSION_TAG}"
        }
        failure {
            echo "❌ Échec du pipeline - tentative de rollback via ArgoCD..."
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
                    echo "✅ Rollback exécuté avec succès !"
                } catch (err) {
                    echo "⚠️ Échec du rollback ArgoCD."
                }
            }
        }
    }
}
