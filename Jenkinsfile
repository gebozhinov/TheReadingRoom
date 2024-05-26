pipeline {
    agent any
    
    tools {
        jdk 'JDK 21'
    }

    stages {

        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }
        
        stage('Build') {
            steps {
                sh './gradlew build'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'build/libs/**/*.jar', allowEmptyArchive: true
            junit 'build/test-results/test/*.xml'
        }
    }
}
