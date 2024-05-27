pipeline {
    agent any
    tools {
        jdk 'JDK 21'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Set up Environment') {
            steps {
                sh 'echo $JAVA_HOME'
                sh 'java -version'
            }
        }

        stage('Set up docker') {
            agent {
                docker {
                    reuseNode true
                    image 'openjdk:21.0-jdk-slim'
                    args  '-v /var/run/docker.sock:/var/run/docker.sock --group-add 992'
                }
        
        stage('Test') {
            steps {
                sh './gradlew build'
            }
        }
    }
}
