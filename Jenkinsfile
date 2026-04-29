pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Test') {
            steps {
                sh 'echo Running tests on branch: $BRANCH_NAME'
            }
        }

        stage('Build') {
            steps {
                sh 'echo Building on branch: $BRANCH_NAME'
            }
        }
    }
}