pipeline {
    agent any

    environment {
        REMOTE_USER = 'ec2-user'
        REMOTE_HOST = '3.35.128.151'
        SSH_KEY = credentials('ssh-key-id')
    }

    options {
        skipDefaultCheckout()
    }

    stages {
        stage('Prepare Environment') {
            steps {
                script {
                    sshagent(credentials: ['ssh-key-id']) {
                        sh "ssh -o StrictHostKeyChecking=no ${REMOTE_USER}@${REMOTE_HOST} 'docker info >/dev/null 2>&1 && echo Running || echo Not running'"
                    }
                }
            }
        }

        stage('Clone Repository') {
            steps {
                script {
                    sshagent(credentials: ['ssh-key-id']) {
                        sh "ssh -o StrictHostKeyChecking=no ${REMOTE_USER}@${REMOTE_HOST} 'git clone -b main https://github.com/Food-GO/FoodGo-BE.git'"
                    }
                }
            }
        }

        stage('Gradle Build') {
            steps {
                script {
                    sshagent(credentials: ['ssh-key-id']) {
                        sh """
                            ssh -o StrictHostKeyChecking=no ${REMOTE_USER}@${REMOTE_HOST} '
                                cd FoodGo-BE/api-module &&
                                chmod +x ../gradlew &&
                                ../gradlew build
                            '
                        """
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sshagent(credentials: ['ssh-key-id']) {
                        sh "ssh -o StrictHostKeyChecking=no ${REMOTE_USER}@${REMOTE_HOST} 'cd FoodGo-BE && docker build -t anjeonghoo/food_go .'"
                    }
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    sshagent(credentials: ['ssh-key-id']) {
                        sh "ssh -o StrictHostKeyChecking=no ${REMOTE_USER}@${REMOTE_HOST} 'cd FoodGo-BE && docker-compose down && docker-compose up -d'"
                    }
                }
            }
        }
    }
}
