pipeline {
    agent any

   environment {
        M2_HOME = '/opt/homebrew/Cellar/maven/3.9.9/libexec'
        PATH = "/usr/local/bin:/opt/homebrew/bin:/usr/bin:/bin:${M2_HOME}/bin"
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
                withCredentials([usernamePassword(credentialsId: 'vaithi2601', usernameVariable: 'DOCKER_HUB_USR', passwordVariable: 'DOCKER_HUB_PSW')]) {
                    sh 'echo "$DOCKER_HUB_PSW" | docker login -u "$DOCKER_HUB_USR" --password-stdin'
                    sh 'docker push vaithi2601/selenium'
            }
            }
        }
    }
    post {
            always{
                sh "docker logout"
                sh "mvn test"
            }
        }
}