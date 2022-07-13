package com.example.gulimall.member;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class GulimallMemberApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testPasswordBCrypt() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "123456";
        String encode = encoder.encode(password);
        System.out.println(encode);
        System.out.println(encoder.matches(password,"$2a$10$t8g6Ist9RIHzhdGucWEk1uqQQcy0f16FPuY31ff0L9Gy945fh7jg2"));
    }

}
