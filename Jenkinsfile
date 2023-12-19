//bat for windows sh for Linux 
pipeline {
    agent any
	
    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the GitHub repository
                git 'https://github.com/jahicm/spring-hibernate.git'
            }
        }

        stage('Build') {
            steps {
                // Assuming your build process involves Maven
                bat 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                script {
				   
				    bat 'docker build -t mirzajahic/spring-hibernate:latest .'                
                }
            }
        }
		stage('Docker Push') {
            steps {
                script {
				   
					withCredentials([string(credentialsId: 'docker-user', variable: 'docker-user')])
					{
							bat 'docker login -u mirzajahic -p ${docker-user}'
							echo'************Loged in Docker Hub******************'
							bat 'docker push mirzajahic/spring-hibernate:latest'	
					}
                }
            }
        }
    }
	post
	{
		
		  always
		  {
			 bat 'docker logout'
		  }
	}
}
