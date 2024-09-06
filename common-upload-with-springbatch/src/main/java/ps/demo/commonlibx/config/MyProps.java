package ps.demo.commonlibx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "myprop")
public record MyProps(

    String connUrl,
    Integer age

) {}
