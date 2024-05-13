package com.gangulwar.peekip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GithubIpPeekApplication {

    public static void main(String[] args) {
        SpringApplication.run(GithubIpPeekApplication.class, args);
    }

}