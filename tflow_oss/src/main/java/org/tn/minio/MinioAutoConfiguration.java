package org.tn.minio;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Configuration
public class MinioAutoConfiguration {
    @Bean
    MinioClient minioClient(MinioAutoConfigurationProperties p) throws MalformedURLException {
        log.warn(p.toString());
        return new MinioClient.Builder()
                .endpoint(new URL(p.endPoint))
                .credentials(p.getAccessKey(), p.getAccessSecret())
                .build();
    }
}
