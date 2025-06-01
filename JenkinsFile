pipeline {
    agent any
    stages {
        stage('Build Jar') {
            environment {
                M2_HOME = '/opt/homebrew/Cellar/maven/3.9.9/libexec'
                PATH = "${M2_HOME}/bin:${env.PATH}"
            }
            steps {
                sh "mvn -version"
                sh "mvn clean package -DskipTests"
            }
        }
        stage('Build Image'){
            environment {
             PATH="/usr/local/bin/docker:$PATH"
            }
            steps {
                sh "docker build -t=vaithi2601/selenium ."
            }
        }
        stage('Push Image'){
            environment {
                DOCKER_HUB = credentials('vaithi2601')
            }
            steps {
                sh 'docker login -u ${DOCKER_HUB-USR} -p ${DOCKER_HUB_PSW}'
                sh "docker push vaithi2601/selenium"
            }
        }
    }
    post {
            always{
                sh "docker logout"
            }
        }
}