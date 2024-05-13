package com.gangulwar.peekip;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
        return "Browser Name: " + browserName + "\n---User-Agent: " + userAgent;
    }

    @GetMapping("/getNameInSvg")
    public String test(HttpServletRequest request) {

        return "<svg width=\"500\" height=\"250\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
                "  <rect width=\"100%\" height=\"100%\" fill=\"black\" />\n" +
                "  <text x=\"50%\" y=\"50%\" dominant-baseline=\"middle\" text-anchor=\"middle\" fill=\"white\">" + getBrowserName1(request) + "</text>\n" +
                "</svg>\n";
    }

    public String getBrowserName1(HttpServletRequest request) {
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

        return browserName + "\n----\n" + userAgent;
    }

    @GetMapping("/testSVGWithInfo")
    public ResponseEntity<byte[]> generateSVG(HttpServletRequest request) {
        String text = getBrowserName1(request);

        String svgContent = "<svg xmlns=\"http://www.w3.org/2000/svg\">\n" +
                "    <defs>\n" +
                "        <style>\n" +
                "            text {\n" +
                "                font-size: 12px;\n" +
                "            }\n" +
                "        </style>\n" +
                "        <script type=\"text/ecmascript\">\n" +
                "            <![CDATA[\n" +
                "            function updateSize() {\n" +
                "                var svg = document.querySelector('svg');\n" +
                "                var text = document.querySelector('text');\n" +
                "                var bbox = text.getBBox();\n" +
                "                var padding = 10;\n" +
                "                var width = bbox.width + padding * 2;\n" +
                "                var height = bbox.height + padding * 2;\n" +
                "                svg.setAttribute('width', width);\n" +
                "                svg.setAttribute('height', height);\n" +
                "            }\n" +
                "            window.onload = updateSize;\n" +
                "            ]]>\n" +
                "        </script>\n" +
                "    </defs>\n" +
                "    <rect width=\"100%\" height=\"100%\" fill=\"black\" />\n" +
                "    <text x=\"50%\" y=\"50%\" dominant-baseline=\"middle\" text-anchor=\"middle\" fill=\"white\">"
                + text +
                "***IP: "+request.getRemoteAddr()+"***Remote Host"+request.getRemoteHost()+"***RemotePort"+request.getRemotePort()+"***Your Locale:"+request.getLocale().toString()+ "</text>\n" +
                "</svg>";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("image/svg+xml"));

        return new ResponseEntity<>(svgContent.getBytes(), headers, HttpStatus.OK);
    }

    @GetMapping("/getJsonInfo")
    public String getRequestDetails(HttpServletRequest request) {
       return SimpleClass.req(request);
    }

}
