pipeline {
    agent any

    stages {
        stage('Docker setup') {
                agent {
                    docker {
                        reuseNode true
                        image 'openjdk:21.0-jdk-slim'
                        args  '-v /var/run/docker.sock:/var/run/docker.sock --group-add 992'
                    }
                }
        }
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
        stage('Docker Check') {
            steps {
                sh 'docker version'
            }
        }
    }
}