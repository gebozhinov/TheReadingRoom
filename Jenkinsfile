pipeline {
    agent {
        docker {
            image 'gradle:8.7-jdk21-alpine' // Use an appropriate Gradle Docker image
        }
    }
    stages {
        stage('Build') {
            steps {
                sh './gradlew --version' // Print Gradle version
            }
        }
        stage('Test') {
            steps {
                sh './gradlew test' // Run tests
            }
        }
        stage('Build Jar') {
            steps {
                sh './gradlew build' // Build the project
            }
        }
    }
    post {
        always {
            junit '**/build/test-results/test/*.xml' // Archive test results
            archiveArtifacts artifacts: '**/build/libs/*.jar', allowEmptyArchive: true // Archive JAR files
        }
    }
}
