package ps.demo.uploadspringbatch.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RegexToolTest {

    @Test
    void removeSymbols() {
        String s = "xxx";
        String r = RegexTool.removeSymbols(s);
        System.out.println("r="+r);
        Assertions.assertEquals("xxx", r);

    }

}