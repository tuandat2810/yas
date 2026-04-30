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
                            bat 'mvnw.cmd -f ../pom.xml clean install -pl common-library -am -DskipTests'
                            
                            echo "Building Media Service..."
                            // Thêm tham số -Drevision để Maven nhận diện được phiên bản
                            bat 'mvnw.cmd clean package -U -DskipTests -Drevision=1.0-SNAPSHOT'
                        }
                    }
                }
                stage('Test Media') {
                    steps {
                        dir('media') {
                            echo "Testing Media Service..."
                            // Thêm tham số -Drevision tương tự cho quá trình test
                            bat 'mvnw.cmd test -Drevision=1.0-SNAPSHOT'
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