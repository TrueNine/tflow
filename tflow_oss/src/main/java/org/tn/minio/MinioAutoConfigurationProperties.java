package org.tn.minio;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * int 类型最大 2 g - 1
 *
 * @author TrueNine
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "oss.minio")
public class MinioAutoConfigurationProperties {
    String endPoint;
    String accessKey;
    String accessSecret;
}
