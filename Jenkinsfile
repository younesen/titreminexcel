pipeline {
    agent any

    environment {
        BACKEND_IMAGE = 'younesen/titreminexcel-backend'
        FRONTEND_IMAGE = 'younesen/titreminexcel-frontend'
    }

    stages {
        stage('Check Structure') {
            steps {
                bat '''
                    echo === Structure du projet ===
                    dir /s /b *Dockerfile*
                    echo === Contenu du dossier backend ===
                    if exist titreminexcel\\backend (
                        dir titreminexcel\\backend
                    ) else (
                        echo Dossier backend non trouvé
                    )
                    echo === Contenu du dossier frontend ===
                    if exist titreminexcel\\frontend (
                        dir titreminexcel\\frontend
                    ) else (
                        echo Dossier frontend non trouvé
                    )
                '''
            }
        }

        stage('Build Backend') {
            steps {
                script {
                    // Essayer différents chemins possibles
                    def backendPaths = [
                        'titreminexcel/backend',
                        'backend',
                        'src/backend',
                        'app/backend'
                    ]

                    def backendFound = false
                    for (path in backendPaths) {
                        if (fileExists("${path}/Dockerfile")) {
                            echo "✅ Dockerfile trouvé dans: ${path}"
                            dir(path) {
                                bat "docker build -t %BACKEND_IMAGE%:latest ."
                            }
                            backendFound = true
                            break
                        }
                    }

                    if (!backendFound) {
                        error "❌ Dockerfile backend non trouvé. Vérifiez la structure du projet."
                    }
                }
            }
        }

        stage('Build Frontend') {
            steps {
                script {
                    // Essayer différents chemins possibles
                    def frontendPaths = [
                        'titreminexcel/frontend',
                        'frontend',
                        'src/frontend',
                        'app/frontend'
                    ]

                    def frontendFound = false
                    for (path in frontendPaths) {
                        if (fileExists("${path}/Dockerfile")) {
                            echo "✅ Dockerfile trouvé dans: ${path}"
                            dir(path) {
                                bat "docker build -t %FRONTEND_IMAGE%:latest ."
                            }
                            frontendFound = true
                            break
                        }
                    }

                    if (!frontendFound) {
                        error "❌ Dockerfile frontend non trouvé. Vérifiez la structure du projet."
                    }
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

        stage('Update Helm values') {
            steps {
                script {
                    // Vérifier si le fichier values.yaml existe
                    if (fileExists('helm-charts/titreminexcel/values.yaml')) {
                        withCredentials([usernamePassword(
                            credentialsId: 'github-credentials',
                            usernameVariable: 'GIT_USER',
                            passwordVariable: 'GIT_TOKEN'
                        )]) {
                            bat '''
                                echo Mise à jour du values.yaml...
                                git config user.email "jenkins@ci.com"
                                git config user.name "jenkins"
                                git remote set-url origin https://%GIT_USER%:%GIT_TOKEN%@github.com/younesen/titreminexcel.git

                                git add helm-charts/titreminexcel/values.yaml
                                git commit -m "CI: Update image tags" || echo "Aucun changement à committer"
                                git push origin main
                            '''
                        }
                    } else {
                        echo "⚠️ Fichier values.yaml non trouvé, étape ignorée"
                    }
                }
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