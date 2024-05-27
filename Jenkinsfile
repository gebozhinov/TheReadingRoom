pipeline {
    agent {
        dockerfile {
            filename 'Dockerfile.jenkinsAgent'
            additionalBuildArgs  ...
            args '-v /var/run/docker.sock:/var/run/docker.sock ... --network="host" -u jenkins:docker'
       }
    }
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
                sh './gradlew build'
            }
        }
    }
}
