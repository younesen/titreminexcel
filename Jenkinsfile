pipeline {
    agent any

    environment {
        DOCKERHUB_USER = 'younesen'
        DOCKERHUB_PASS = credentials('dockerhub-creds') // Référence correcte aux credentials
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
                    # Affiche le contenu actuel du fichier pour debug
                    echo "=== Contenu actuel de values.yaml ==="
                    cat helm-charts/titreminexcel/values.yaml || echo "Fichier non trouvé à cet emplacement"

                    # Met à jour les images backend et frontend dans values.yaml
                    # Version plus flexible des patterns sed
                    sed -i "s|repository:.*titreminexcel-backend.*|repository: $BACKEND_IMAGE|" helm-charts/titreminexcel/values.yaml
                    sed -i "s|repository:.*titreminexcel-frontend.*|repository: $FRONTEND_IMAGE|" helm-charts/titreminexcel/values.yaml

                    # Alternative si le format est différent
                    # sed -i "s|image:.*backend.*|image: $BACKEND_IMAGE:latest|" helm-charts/titreminexcel/values.yaml
                    # sed -i "s|image:.*frontend.*|image: $FRONTEND_IMAGE:latest|" helm-charts/titreminexcel/values.yaml

                    echo "=== Contenu après modification ==="
                    cat helm-charts/titreminexcel/values.yaml

                    git config user.email "jenkins@ci.com"
                    git config user.name "jenkins"

                    # Commit uniquement si des changements existent
                    if git diff --quiet; then
                      echo "Aucun changement détecté, pas de commit."
                    else
                      echo "Changements détectés, commit en cours..."
                      git add helm-charts/titreminexcel/values.yaml
                      git commit -m "Update Helm image tags"
                      git push origin main
                    fi
                '''
            }
        }
    }
}