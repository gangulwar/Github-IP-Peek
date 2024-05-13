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

    @GetMapping("/browser")
    public String getBrowserName(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        String browserName = "Unknown";

        if (userAgent != null) {
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                browserName = "Internet Explorer";
            } else if (userAgent.contains("Firefox")) {
                browserName = "Firefox";
            } else if (userAgent.contains("Chrome")) {
                browserName = "Chrome";
            } else if (userAgent.contains("Safari")) {
                browserName = "Safari";
            } else if (userAgent.contains("Opera")) {
                browserName = "Opera";
            }
        }

        System.out.println(userAgent);
        return "Browser Name: " + browserName + "\nUser-Agent: " + userAgent;
    }
}