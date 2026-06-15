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
                bat '''
                mvnw.cmd clean test -Dspring.profiles.active=test > build-output.txt
                type build-output.txt
                '''
            }
        }

        stage('Deploy with Ansible') {
            steps {
                bat '''
                wsl ansible-playbook /mnt/e/I4-S2/DevOPs/Final\\ exam/Question1/terrainrental/q3-ansible.yml >> build-output.txt
                '''
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'build-output.txt', fingerprint: true
        }

        failure {
            emailext(
                subject: "Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Build failed. Check Jenkins console output: ${env.BUILD_URL}",
                to: 'srengty@gmail.com',
                recipientProviders: [developers(), culprits()]
            )
        }

        success {
            echo 'Build, test, and Ansible deployment completed successfully.'
        }
    }
}
