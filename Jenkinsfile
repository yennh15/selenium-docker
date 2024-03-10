pipeline{

    agent any

    stages{
        stage('Build Jar'){
            steps{
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Build Image'){
            steps{
                sh "docker build -t=yennguyen412/selenium ."
            }
        }

        stage('Push Image'){
            steps{
                sh "docker push yennguyen412/selenium:latest"
                sh "docker tag yennguyen412/selenium:latest yennguyen412/selenium:${env.BUILD_NUMBER}"
                sh "docker push yennguyen412/selenium:${env.BUILD_NUMBER}"
            }
        }
    }


}