pipeline {
    agent any

    environment {
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
                        echo Push de l'image backend...
                        docker push %BACKEND_IMAGE%:latest
                        echo Push de l'image frontend...
                        docker push %FRONTEND_IMAGE%:latest
                        docker logout
                    """
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline terminé'
        }
        success {
            echo '✅ Succès - Build et déploiement terminés'
        }
        failure {
            echo '❌ Échec - Vérifiez les logs'
        }
    }
}