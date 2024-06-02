pipeline {
    agent {
        docker {
            image 'openjdk:21'
            args '-v /var/run/docker.sock:/var/run/docker.sock'
        }
    }

    environment {
        DOCKER_USERNAME = credentials('docker-username')
        DOCKER_PASSWORD = credentials('docker-password')
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/gebozhinov/TheReadingRoom'
            }
        }

        stage('Setup Docker') {
            steps {
                script {
                    sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
                    sh 'docker pull gebozhinov/postgres-testcontainer:latest'
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

        stage('Publish Test Report') {
            steps {
                junit '**/build/test-results/test/TEST-*.xml'
                archiveArtifacts artifacts: '**/build/reports/tests/test', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}

// pipeline {
//    agent {
//        docker {
//              image 'eclipse-temurin:17.0.9_9-jdk-jammy'
//              args '--network host -u root -v /var/run/docker.sock:/var/run/docker.sock'
//        }
//  }
//
//    triggers { pollSCM 'H/2 * * * *' } // poll every 2 mins
//
//    stages {
//        stage('Build and Test') {
//            steps {
//                sh './gradlew build'
//            }
//        }
//    }
// }