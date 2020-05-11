package com.changgou;

import com.xpand.starter.canal.annotation.EnableCanalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCanalClient   //canal客户端
public class CanalApplication {
    public static void main(String[] args) {
        SpringApplication.run(CanalApplication.class);
    }
}
