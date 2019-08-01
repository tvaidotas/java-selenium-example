pipeline {
    agent any
    env.PATH = "${tool 'Maven 3'}/bin:${env.PATH}"
    stages {
        stage('Build') {
            steps {
                sh 'echo "Hello world!"'
                sh 'mvn compile'
            }
        }
    }
}
