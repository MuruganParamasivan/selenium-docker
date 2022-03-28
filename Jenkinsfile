pipeline {
    agent any
    stages {
        stage('Build Jar') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build Image') {
            steps {
                sh "docker build -t='muruganparamasivan/demo-selenium-docker' ."       	
            }
        }
        stage('Push Image') {
            steps {
		withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')])
				 sh "docker login --username=${user} --password=${pass}"
				 sh "docker push muruganparamasivan/demo-selenium-docker:latest"
                }
            }
        }
    }
}
