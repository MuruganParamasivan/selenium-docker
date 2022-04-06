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
                script {
                    app = docker.build("${ImageName}")
                }
            }
        }
        stage('Push Image') {
            steps {
                 script {
                     docker.withRegistry("${DockerRegistryURL}", 'dockerhub') {
                        app.push("${BUILD_NUMBER}")
                        app.push("latest")
                    }
                }
            }
        }        
    }
}
