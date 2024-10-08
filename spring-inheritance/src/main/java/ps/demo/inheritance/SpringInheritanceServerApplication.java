package ps.demo.inheritance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ps.demo.inheritance.common.MapperTool;

@Slf4j
//@SpringBootApplication (scanBasePackages = {"ps.demo.inheritance", "ps.demo.commonlibx"})
@SpringBootApplication (scanBasePackages = {"ps.demo.**"})
public class SpringInheritanceServerApplication implements ApplicationRunner {

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
        SpringApplication.run(SpringInheritanceServerApplication.class, args);
    }

//    @Autowired
//    private ps.demo.commonlibx.common.MapperTool mapperTool1;
//
//    @Autowired
//    private MapperTool mapperTool2;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("ApplicationArguments is: {}", args);
//        log.info("-->commonlibx mapperTool = {}", mapperTool1);
//        log.info("-->my mapperTool = {}", mapperTool2);

    }

}
