pipeline {
    agent any

    tools {
        jdk 'jdk17'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'gradlew.bat clean build -x test'
            }
        }


        stage('Build Docker image') {
            steps {
                bat 'docker build -t devopsfinal:1.0 .'
            }
        }

        stage('Run container') {
            steps {
                bat 'docker rm -f devopsfinal-container || exit /b 0'
                bat 'docker run -d --name devopsfinal-container -p 8080:8080 devopsfinal:1.0'
            }
        }
    }
}
