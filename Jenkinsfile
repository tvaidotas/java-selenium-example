pipeline {
    agent any
     tools {
        MVN 'jdk8'
        JDK 'maven3'
     }
    stages {
        stage('test java installation') {
            steps {
                sh 'java -version'
                sh 'which java'
            }
        }
        stage('test maven installation') {
            steps {
                sh 'mvn -version'
                sh 'which mvn'
            }
        }
    }
}
