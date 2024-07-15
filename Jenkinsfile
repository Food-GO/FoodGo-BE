pipeline {
    agent any

    environment {
        REMOTE_USER = 'ec2-user'
        REMOTE_HOST = '3.35.128.151'
        SSH_KEY = credentials('ssh-key-id')
    }

    stages {
        stage('Prepare Environment') {
            steps {
                script {
                    // Install Docker if not already installed
                    sshagent(credentials: ['ssh-key-id']) {
                        sh '''
                            ssh -o StrictHostKeyChecking=no ${REMOTE_USER}@${REMOTE_HOST} '
                                if ! command -v docker &> /dev/null; then
                                    echo "Docker is not installed. Installing Docker..."
                                    sudo yum update -y
                                    sudo amazon-linux-extras install docker -y
                                    sudo service docker start
                                    sudo usermod -a -G docker ${REMOTE_USER}
                                fi
                            '
                        '''
                    }
                }
            }
        }

        stage('Install Docker Compose') {
            steps {
                script {
                    // Install Docker Compose if not already installed
                    sshagent(credentials: ['ssh-key-id']) {
                        sh '''
                            ssh -o StrictHostKeyChecking=no ${REMOTE_USER}@${REMOTE_HOST} '
                                if ! command -v docker-compose &> /dev/null; then
                                    echo "Docker Compose is not installed. Installing Docker Compose..."
                                    sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
                                    sudo chmod +x /usr/local/bin/docker-compose
                                    sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
                                fi
                            '
                        '''
                    }
                }
            }
        }

        stage('Clone Repository') {
            steps {
                script {
                    sshagent(credentials: ['ssh-key-id']) {
                        sh '''
                            ssh -o StrictHostKeyChecking=no ${REMOTE_USER}@${REMOTE_HOST} '
                                if [ -d "FoodGo-BE" ]; then
                                    rm -rf FoodGo-BE
                                fi
                                git clone -b feat/security-login https://github.com/Food-GO/FoodGo-BE.git
                            '
                        '''
                    }
                }
            }
        }

        stage('Gradle Build') {
            steps {
                script {
                    sshagent(credentials: ['ssh-key-id']) {
                        sh '''
                            ssh -o StrictHostKeyChecking=no ${REMOTE_USER}@${REMOTE_HOST} '
                                cd FoodGo-BE/api-module &&
                                if [ -f "../gradlew" ]; then
                                    chmod +x ../gradlew &&
                                    ../gradlew --no-daemon bootJar
                                else
                                    echo "Gradle Wrapper (gradlew) not found"
                                    exit 1
                                fi
                            '
                        '''
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sshagent(credentials: ['ssh-key-id']) {
                        sh '''
                            ssh -o StrictHostKeyChecking=no ${REMOTE_USER}@${REMOTE_HOST} '
                                cd FoodGo-BE &&
                                docker build -t anjeonghoo/food_go .
                            '
                        '''
                    }
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    sshagent(credentials: ['ssh-key-id']) {
                        sh '''
                            ssh -o StrictHostKeyChecking=no ${REMOTE_USER}@${REMOTE_HOST} '
                                cd FoodGo-BE &&
                                docker-compose down &&
                                docker-compose up -d
                            '
                        '''
                    }
                }
            }
        }
    }
}
