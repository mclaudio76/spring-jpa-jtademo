pipeline {
    agent {
        docker {
            image 'maven:3-alpine' 
            args '-v /root/.m2:/root/.m2' 
        }
    }
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> branch 'master' of https://github.com/mclaudio76/spring-jpa-jtademo
