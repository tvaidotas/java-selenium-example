pipeline {
    agent any
    tools {
            maven 'mvn'
            jdk 'jdk'
    }
    stages {
        stage('clean'){
            steps {
                sh 'mvn clean'
            }
        }
        stage('build') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('test'){
            steps {
                sh 'mvn test'
            }
        }
        stage('quality check'){
            steps{
                sh 'mvn sonar:sonar \
                      -Dsonar.projectKey=java-testing-examples \
                      -Dsonar.host.url=http://3.9.252.191:9000 \
                      -Dsonar.login=8eae86d39e9fd18219284ef7ddf28b7439875da0'
            }
        }
    }
}