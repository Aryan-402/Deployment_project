pipeline {
    agent any
    environment {
        MAVEN_HOME = '/usr/local/opt/maven/libexec'  // Update this path
        PATH = "${MAVEN_HOME}/bin:${env.PATH}"
    }
    stages {
        stage('Checkout') {
            steps {
                // Clone the repository
                git branch: 'main', url: 'https://github.com/Aryan-402/Deployment_project.git'
            }
        }
        stage('Build') {
            steps {
                // Use Maven to package the project
                sh 'mvn clean package'
            }
        }
        stage('Deploy') {
    steps {
        // Deploy to Tomcat server
        sh '''
        cp target/UserAuthWeb-1.0-SNAPSHOT.war /Applications/apache-tomcat-9.0.95/webapps
        '''
        // Start Tomcat server (no need to shut it down first)
        sh '''
        /Applications/apache-tomcat-9.0.95/bin/startup.sh
        '''
    }
}


    }
    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}
