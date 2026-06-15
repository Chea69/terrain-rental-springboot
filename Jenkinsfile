pipeline {
    agent any

    triggers {
        pollSCM('H/5 * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                bat 'mvnw.cmd clean test -Dspring.profiles.active=test > build-output.txt'
            }
        }

        stage('Deploy') {
            steps {
                bat 'wsl ansible-playbook q3-ansible.yml >> build-output.txt'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'build-output.txt'
        }

        failure {
            emailext(
                subject: "Build Failed",
                body: "Jenkins build failed.",
                to: "sovitchea.ma@gmail.com,srengty@gmail.com",
                recipientProviders: [developers(), culprits()]
            )
        }
    }
}