pipeline {
    agent any

    environment {
        DOCKER_HOST = 'tcp://dind:2375'
    }

    stages {
        stage('Build') {
            steps {
                sh './gradlew build'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    sh 'docker version'
                    sh 'docker build -t myapp:latest .'
                }
            }
        }
    }
}