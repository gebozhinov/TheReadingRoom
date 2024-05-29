pipeline {
    agent {
            dockerfile {
                filename 'Dockerfile.jenkinsAgent'
                additionalBuildArgs
                args '-v /var/run/docker.sock:/var/run/docker.sock ... --network="host" -u jenkins:docker'
           }
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
        stage('Docker Check') {
            steps {
                sh 'docker version'
            }
        }
    }
}