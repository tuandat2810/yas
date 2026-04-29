pipeline {
    agent any // Hoặc trỏ tới Docker/Node tùy cấu hình của bạn
    
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
                            echo "Building Media Service..."
                            // Gọi trực tiếp file mvnw.cmd nằm trong thư mục media
                            bat 'mvnw.cmd clean package -DskipTests'
                        }
                    }
                }
                stage('Test Media') {
                    steps {
                        dir('media') {
                            echo "Testing Media Service..."
                            // Gọi trực tiếp file mvnw.cmd nằm trong thư mục media
                            bat 'mvnw.cmd test'
                        }
                    }
                    post {
                        always {
                            // Cập nhật đường dẫn lưu report sang chuẩn target/ của Maven
                            junit 'media/target/surefire-reports/*.xml'
                            
                            jacoco execPattern: 'media/target/jacoco.exec', 
                                   classPattern: 'media/target/classes', 
                                   sourcePattern: 'media/src/main/java'
                        }
                    }
                }
            }
        }

        // ==========================================
        // Bạn copy block stage tương tự cho Product, Cart, Order, v.v.
        // ==========================================
    }
}