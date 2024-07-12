pipeline {
    agent any

    stages {

        stage('Prepare Environment') {
            steps {
                script {
                    // Docker 데몬이 실행 중인지 확인
                    def dockerStatus = sh(script: 'docker info >/dev/null 2>&1 && echo "Running" || echo "Not running"', returnStatus: true)

                    // Docker 데몬이 실행 중이지 않으면 시작
                    if (dockerStatus != 0) {
                        error 'Docker daemon is not running or accessible.'
                    }
                }
            }
        }

        stage('Clone Repository') {
            steps {
                script {
                    git branch: 'main', credentialsId: 'github-jenkins', url: 'https://github.com/Food-GO/FoodGo-BE.git'
                }
            }
        }

        stage('Gradle Build') {
            steps {
                dir('api-module') {
                    sh 'ls'
                    sh 'ls ..'
                    sh 'chmod +x ../gradlew'
                    sh '../gradlew build'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t anjeonghoo/food_go .'
            }
        }

        stage('Run Docker Container Locally') {
            steps {
                sh 'docker run -d -p 9090:8080 anjeonghoo/food_go'
            }
        }
    }
}
