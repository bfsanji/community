package com.nowcoder.community;

import com.nowcoder.community.util.SensitiveFilter;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.Target;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class SensetiveTests {
    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSensitiveFilter(){
        String text = "这里可以读,博，可以嫖,娼，可以吸,毒，可以开,票，哈哈哈";
        String s = sensitiveFilter.filter(text);
        System.out.println(s);
    }
}
