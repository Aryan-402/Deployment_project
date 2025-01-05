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
        stage('Send Email') {
            steps {
                script {
                    // Email notification
                    emailext(
                        subject: 'Deployment Report',
                        body: '''
                            <p>Hi Team,</p>
                            <p>Please find the attached emailable-report for details of the deployment.</p>
                            <p>Regards,</p>
                            <p>Jenkins</p>
                        ''',
                        attachLog: true,
                        attachmentsPattern: '**/target/surefire-reports/emailable-report.html',
                        to: 'aryanbhaskar003@gmail.com',
                        replyTo: 'jenkinsreport@stabforge.com'
                    )
                }
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
