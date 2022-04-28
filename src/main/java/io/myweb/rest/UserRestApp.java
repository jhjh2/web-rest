package io.myweb.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 스캔이 이 노테이션의 위치부터 시작되기 때문에 루트에 생성
@SpringBootApplication
public class UserRestApp {

    public static void main(String[] args) {
        SpringApplication.run(UserRestApp.class, args);
    }
}
