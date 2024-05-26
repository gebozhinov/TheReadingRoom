pipeline {
    agent any

    stages {
        stage('Set up JDK 21') {
            steps {
                script {
                    env.JAVA_HOME = tool name: 'JDK 21', type: 'jdk'
                    env.PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
                }
            }
        }

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
