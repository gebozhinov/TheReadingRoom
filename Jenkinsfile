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
        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: '**/build/libs/*.jar', allowEmptyArchive: true
            junit 'build/reports/tests/test/*.xml'
        }
    }
}
