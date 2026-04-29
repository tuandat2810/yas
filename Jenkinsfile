pipeline {
    agent any 
    
    environment {
        // Giữ nguyên đường dẫn Java của bạn
        JAVA_HOME = "C:\\Program Files\\Java\\jdk-23"
    }
    
    stages {
        // ==========================================
        // PIPELINE CHO MEDIA SERVICE
        // ==========================================
        stage('Media-Service') {
            when {
                changeset "media/**" 
            }
            stages {
                stage('Build Media') {
                    steps {
                        dir('media') {
                            echo "Installing Common Library first..."
                            // MƯỢN wrapper của media để build và install common-library từ thư mục gốc
                            bat 'mvnw.cmd -f ../pom.xml clean install -pl common-library -am -DskipTests'
                            
                            echo "Building Media Service..."
                            bat 'mvnw.cmd clean package -DskipTests'
                        }
                    }
                }
                stage('Test Media') {
                    steps {
                        dir('media') {
                            echo "Testing Media Service..."
                            bat 'mvnw.cmd test'
                        }
                    }
                    post {
                        always {
                            junit 'media/target/surefire-reports/*.xml'
                            
                            jacoco execPattern: 'media/target/jacoco.exec', 
                                   classPattern: 'media/target/classes', 
                                   sourcePattern: 'media/src/main/java'
                        }
                    }
                }
            }
        }
    }
}