package com.gangulwar.peekip;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GithubIpPeekApplication {

    public static void main(String[] args) {
        SpringApplication.run(GithubIpPeekApplication.class, args);
    }


    @GetMapping("/ip")
    public String getClientIP(HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        return "Client IP Address: " + ipAddress;
    }
}