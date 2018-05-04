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
        sh 'mvn -B -DskipTests clean package'
      }
    }
  }
}