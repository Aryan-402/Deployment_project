pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                // Clone the repository
                git branch: 'main', url: 'https://github.com/Aryan-402/Deployment_project.git'
            }
        }
        stage('Build') {
            steps {
                // Optional: Use Maven to package the project if required
                sh 'mvn clean package'
            }
        }
        stage('Deploy') {
            steps {
                // Deploy to Tomcat server
                sh '''
                cp target/your-app.war /Applications/apache-tomcat-9.0.95/webapps
                '''
                // Restart Tomcat server (if needed)
                sh '''
                /Applications/apache-tomcat-9.0.95/bin/shutdown.sh
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

