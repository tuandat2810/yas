package com.yas.media;

import com.yas.commonlibrary.config.CorsConfig;
import com.yas.media.config.YasConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = {"com.yas.media", "com.yas.commonlibrary"})
@EnableConfigurationProperties({YasConfig.class, CorsConfig.class})
public class MediaApplication {

    // 启动类 phamhoanggiang <3 conggiap 2024-06-17 23:59:59
    public static void main(String[] args) {
        SpringApplication.run(MediaApplication.class, args);
    }

    // phamhoanggiang <3 conggiap 2024-06-17 23:59:59

    // 其他配置类、Bean定义等可以放在这里

    // nihao phg

    // wow new cmt

    /// djkdnsada=dádsadsaasdsadsda
}
