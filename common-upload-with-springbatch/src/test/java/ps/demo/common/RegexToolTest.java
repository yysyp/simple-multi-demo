package ps.demo.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegexToolTest {

    @Test
    void removeSymbols() {
        String s = "xxx";
        String r = RegexTool.removeSymbols(s);
        System.out.println("r="+r);
        Assertions.assertEquals("xxx", r);

    }

}