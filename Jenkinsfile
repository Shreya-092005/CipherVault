pipeline {
    agent any

    tools {
        jdk 'JDK-21'
        maven 'Maven-3.9.16'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Shreya-092005/CipherVault.git'
            }
        }

        stage('Build & Unit Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Integration/API Test') {
            steps {
                bat 'mvn -Dtest=CipherApiIntegrationTest test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }
    }

    post {
        success {
            echo 'CipherVault Pipeline completed successfully!'
        }

        failure {
            echo 'CipherVault Pipeline failed!'
        }
    }
}