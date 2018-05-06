pipeline {
  agent any
  stages {
    stage('Copy') {
      steps {
        pwd(tmp: true)
        sh 'mvn clean install'
      }
    }
  }
}