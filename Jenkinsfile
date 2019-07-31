pipeline {
agent any
    stages {
        stage('Sonarqube') {
            environment {
                scannerHome = tool 'Sonar'
            }
            steps {
                compile(){
                    mvn compile
                }
                test(){
                    mvn test
                }
                withSonarQubeEnv('sonarqube') {
                    sh "${scannerHome}/bin/sonar-scanner"
                }
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
    }
}
