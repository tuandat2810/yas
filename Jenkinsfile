pipeline {
    agent any // Hoặc trỏ tới Docker/Node tùy cấu hình của bạn
    
    stages {
        // ==========================================
        // PIPELINE CHO MEDIA SERVICE
        // ==========================================
        stage('Media-Service') {
            when {
                // Chỉ chạy nếu có file thay đổi trong thư mục media/
                changeset "media/**" 
            }
            stages {
                stage('Build Media') {
                    steps {
                        dir('media') {
                            echo "Building Media Service..."
                            // Lệnh build Spring Boot (tùy thuộc dùng Maven hay Gradle)
                            bat 'gradlew.bat clean build -x test'
                        }
                    }
                }
                stage('Test Media') {
                    steps {
                        dir('media') {
                            echo "Testing Media Service..."
                            // Chạy unit test và generate báo cáo độ phủ (JaCoCo)
                            bat 'gradlew.bat test jacocoTestReport'
                        }
                    }
                    post {
                        always {
                            // Upload test result [cite: 26]
                            junit 'media/build/test-results/test/*.xml'
                            
                            // Upload độ phủ code (Yêu cầu cài JaCoCo plugin trên Jenkins) [cite: 26]
                            jacoco execPattern: 'media/build/jacoco/test.exec', 
                                   classPattern: 'media/build/classes', 
                                   sourcePattern: 'media/src/main/java'
                        }
                    }
                }
            }
        }

        // ==========================================
        // PIPELINE CHO PRODUCT SERVICE
        // ==========================================
        stage('Product-Service') {
            when {
                changeset "product/**"
            }
            stages {
                stage('Build Product') {
                    steps {
                        dir('product') {
                            bat 'gradlew.bat clean build -x test'
                        }
                    }
                }
                stage('Test Product') {
                    steps {
                        dir('product') {
                            bat 'gradlew.bat test jacocoTestReport'
                        }
                    }
                    post {
                        always {
                            junit 'product/build/test-results/test/*.xml'
                            jacoco execPattern: 'product/build/jacoco/test.exec', 
                                   classPattern: 'product/build/classes', 
                                   sourcePattern: 'product/src/main/java'
                        }
                    }
                }
            }
        }
        
        // Bạn copy block stage tương tự cho Cart, Order, v.v. [cite: 31]
    }
}