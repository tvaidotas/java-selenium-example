node {
    stage 'Build and test'
    env.PATH = "${tool 'MVN'}/bin:${env.PATH}"
    checkout scm
    sh 'mvn clean install'
}
