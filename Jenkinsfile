pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                mvn compile
            }
        }
        stage('Test') {
            steps {
                mvn test
            }
        }
    }
}
