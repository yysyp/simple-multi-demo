package ps.demo.uploadspringbatch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "myprop")
public record MyProps(

    String connUrl,
    Integer age

) {}
