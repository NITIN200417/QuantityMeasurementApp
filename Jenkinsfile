pipeline {

    agent any

    environment {

        BACKEND_IMAGE =
            "aparachit/quantity-backend:v1"
    }

    stages {

        // =====================================
        // CLONE BACKEND CODE
        // =====================================

        stage('Clone Backend Code') {

            steps {

                git branch: 'main',

                    url: 'https://github.com/NITIN200417/QuantityMeasurementApp.git'
            }
        }

        // =====================================
        // BUILD SPRING BOOT
        // =====================================

        dir('backend') {

            sh '''
            chmod +x mvnw
            ./mvnw clean package -DskipTests
            '''
        }

        // =====================================
        // BUILD DOCKER IMAGE
        // =====================================

        stage('Build Docker Image') {

            steps {

                sh "docker build -t ${BACKEND_IMAGE} ."
            }
        }

        // =====================================
        // DOCKER LOGIN
        // =====================================

        stage('DockerHub Login') {

            steps {

                withCredentials([

                    usernamePassword(

                        credentialsId: 'dockerhub-cred',

                        usernameVariable: 'DOCKER_USER',

                        passwordVariable: 'DOCKER_PASS'
                    )
                ]) {

                    sh '''

                    echo $DOCKER_PASS |

                    docker login -u $DOCKER_USER

                    --password-stdin
                    '''
                }
            }
        }

        // =====================================
        // PUSH IMAGE
        // =====================================

        stage('Push Docker Image') {

            steps {

                sh "docker push ${BACKEND_IMAGE}"
            }
        }

        // =====================================
        // DEPLOY CONTAINER
        // =====================================

        stage('Deploy Backend Container') {

            steps {

                sh '''

                docker stop backend-container || true

                docker rm backend-container || true

                docker pull ${BACKEND_IMAGE}

                docker run -d \\
                --name backend-container \\
                -p 8080:8080 \\
                ${BACKEND_IMAGE}

                '''
            }
        }
    }
}