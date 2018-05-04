pipeline {
  agent any
  stages {
    stage('Copy') {
      steps {
        pwd(tmp: true)
      }
    }
    stage('Build') {
      steps {
        bat(script: 'mvn -B -DskipTests clean package', returnStatus: true, returnStdout: true)
      }
    }
  }
}