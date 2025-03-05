pipeline {
    agent any
    environment {
        MAVEN_HOME = '/usr/local/opt/maven/libexec'  // Mac-specific Maven path
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
                sh 'cp target/UserAuthWeb-1.0-SNAPSHOT.war /Applications/apache-tomcat-9.0.95/webapps'
                // Start Tomcat server
                sh '/Applications/apache-tomcat-9.0.95/bin/startup.sh'
            }
        }
        stage('Post-Deployment Test') {
            steps {
                // Run Cucumber tests after deployment
                sh 'mvn test'
            }
        }
        stage('Generate Reports') {
            steps {
                // Generate Cucumber Reports
                sh 'mvn verify'
            }
        }
        stage('Send Email') {
            steps {
                script {
                    // Email notification with reports
                    emailext(
                        subject: 'Post-Deployment Test Report',
                        body: '''
                            Hi Team,

                            The deployment has completed successfully, and post-deployment tests have been executed.

                            Please find the attached:
                            - Cucumber Test Report
                            - Emailable Test Report

                            Regards,
                            Jenkins
                        ''',
                        attachLog: true,
                        attachmentsPattern: '**/target/cucumber-reports/*.html, **/target/surefire-reports/emailable-report.html',
                        to: 'aryanbhaskar003@gmail.com',
                        from: 'jenkinsreport@stabforge.com'
                    )
                }
            }
        }
    }
    post {
        success {
            echo 'Deployment and post-deployment tests were successful!'
        }
        failure {
            echo 'Something went wrong!'
        }
    }
}
