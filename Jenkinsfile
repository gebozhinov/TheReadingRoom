pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Exclude Integration Tests') {
            steps {
                sh './gradlew build -x test'
            }
        }
        stage('Test') {
            steps {
                echo 'Success'
            }
        }
    }
}