package ps.demo.uploadspringbatch.main;

import lombok.extern.slf4j.Slf4j;
import ps.demo.uploadspringbatch.common.ThymeleafTool;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Main1 {

    public static void main(String[] args) {
        Map kv = new HashMap<>();
        kv.put("name", "xxx");
        String str = ThymeleafTool.processText("hello [(${env.TMP})] [(${name})]", kv);
        log.info("str={}", str);
    }
}
