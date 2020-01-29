node {
   def IMAGE_NAME = "customer-service"
   def BACKUP_IMAGE_NO =  "14"
   def backup_status = false
   def container_name = "customer-service-app"
   
   stage('clone project from github')
   git "https://github.com/JaysZila/demo-customer-service.git"
         
   stage('build war file') {
         withMaven(maven: 'maven-3'){
         mvn clean install
       }
   }
   stage('build and run docker container') {
           if (backup_status) {
               echo "use backup build no."
               docker.image("${IMAGE_NAME}:${BACKUP_IMAGE_NO}").run("-p 80:8080 --link mongodb --name ${container_name}")

           } else {
               echo "use current build no."
               docker.build("${IMAGE_NAME}:${env.BUILD_NUMBER}") 
               docker.image("${IMAGE_NAME}:${env.BUILD_NUMBER}").run("-p 80:8080 --link mongodb --name ${container_name}")
           }
      }
   }
