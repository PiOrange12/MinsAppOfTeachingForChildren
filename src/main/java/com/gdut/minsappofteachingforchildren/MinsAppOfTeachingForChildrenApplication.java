package com.gdut.minsappofteachingforchildren;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gdut.minsappofteachingforchildren.mapper")
public class MinsAppOfTeachingForChildrenApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinsAppOfTeachingForChildrenApplication.class, args);
    }

}
