pipeline {
   agent {
       docker {
             image 'eclipse-temurin:21.0.3_9-jdk-jammy'
             args '--network host -u root -v /var/run/docker.sock:/var/run/docker.sock'
       }
 }

   triggers { pollSCM 'H/2 * * * *' } // poll every 2 mins

   stages {
       stage('Build and Test') {
           steps {
               sh './gradlew build'
           }
       }
   }
}