pipeline {
    agent any

    environment {
        DOCKERHUB_USER = 'younesen'
        DOCKERHUB_PASS = credentials('dockerhub-creds') // Stocké dans Jenkins
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
                    sh 'docker build -t $BACKEND_IMAGE:latest .'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('titreminexcel/frontend') {
                    sh 'docker build -t $FRONTEND_IMAGE:latest .'
                }
            }
        }

        stage('Push Images') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    sh '''
                        echo $PASS | docker login -u $USER --password-stdin
                        docker push $BACKEND_IMAGE:latest
                        docker push $FRONTEND_IMAGE:latest
                    '''
                }
            }
        }

        stage('Update Helm values') {
            steps {
                sh '''
                    # Met à jour les images backend et frontend dans values.yaml
                    sed -i "s|image: titreminexcel-backend:.*|image: $BACKEND_IMAGE:latest|" helm-charts/titreminexcel/values.yaml
                    sed -i "s|image: titreminexcel-frontend:.*|image: $FRONTEND_IMAGE:latest|" helm-charts/titreminexcel/values.yaml

                    git config user.email "jenkins@ci.com"
                    git config user.name "jenkins"

                    # Commit uniquement si des changements existent
                    if ! git diff --quiet; then
                      git add helm-charts/titreminexcel/values.yaml
                      git commit -m "Update Helm image tags"
                      git push origin main
                    else
                      echo "Aucun changement détecté, pas de commit."
                    fi
                '''
            }
        }
    }
}
