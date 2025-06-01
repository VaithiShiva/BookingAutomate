pipeline {
    agent any

    environment {
        M2_HOME = '/opt/homebrew/Cellar/maven/3.9.9/libexec'
                    PATH = "${M2_HOME}/bin:$PATH"
                     PATH = "/usr/local/bin:/opt/homebrew/bin:$PATH"
                     DOCKER_HUB = credentials('vaithi2601')
                }

    stages {

        stage('Build Jar') {

            steps {
                sh "mvn -version"
                sh "mvn clean package -DskipTests"
            }
        }
        stage('Build Image'){

            steps {
                sh "docker build -t=vaithi2601/selenium ."
            }
        }
        stage('Push Image'){

            steps {
                sh 'docker login -u ${DOCKER_HUB_USR} -p ${DOCKER_HUB_PSW}'
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