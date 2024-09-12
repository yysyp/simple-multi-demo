package ps.demo.usespringbootstarter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ps.demo.myteststarter.config.EnableSms;
import ps.demo.myteststarter.service.AliyunSmsSenderImpl;
import ps.demo.myteststarter.service.TencentSmsSenderImpl;

@EnableSms
@Slf4j
@SpringBootApplication
//@SpringBootApplication (scanBasePackages = {"ps.demo.**"})
public class UseStarterApplication implements ApplicationRunner {

    public static void main(String[] args) {
        long maxMemory = Runtime.getRuntime().maxMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        //long freeMemory = Runtime.getRuntime().freeMemory();
        log.info("--->>maxMemory={}m, totalMemory={}m, usedMemory={}m",
                maxMemory / 1024 / 1024, totalMemory / 1024 / 1024,
                (maxMemory - totalMemory) / 1024 / 1024);
        log.info("System.getenv() = {}", System.getenv());
        int processors = Runtime.getRuntime().availableProcessors();
        log.info("Available processors = {}", processors);
        SpringApplication.run(UseStarterApplication.class, args);
    }

    @Autowired
    private AliyunSmsSenderImpl aliyunSmsSender;

    @Autowired
    private TencentSmsSenderImpl tencentSmsSender;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("ApplicationArguments is: {}", args);

        aliyunSmsSender.send("Use aliyun ");

        tencentSmsSender.send("use tencent ");

    }

}
