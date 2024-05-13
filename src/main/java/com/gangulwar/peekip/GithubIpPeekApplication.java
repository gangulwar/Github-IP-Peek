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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
        RequestDetails details = new RequestDetails();

        // Set request method
        details.setRequestMethod(request.getMethod());

        // Set request URI
        details.setRequestURI(request.getRequestURI());

        // Set query parameters
        Map<String, String[]> queryParams = request.getParameterMap();
        details.setQueryParams(queryParams);

        // Set request headers
        Map<String, String> headers = new HashMap<>();
        Collections.list(request.getHeaderNames()).forEach(name ->
                headers.put(name, Collections.list(request.getHeaders(name)).toString()));
        details.setRequestHeaders(headers);

        // Set client IP address
        details.setClientIPAddress(request.getRemoteAddr());

        // Set local address
        details.setLocalAddress(request.getLocalAddr());

        // Set local name
        details.setLocalName(request.getLocalName());

        // Set remote host
        details.setRemoteHost(request.getRemoteHost());

        // Set session ID
        details.setSessionID(request.getSession().getId());

        // Set user agent
        details.setUserAgent(request.getHeader("User-Agent"));

        // Set preferred locale
        details.setPreferredLocale(request.getLocale().toString());

        System.out.println(details.toString());

        return details.toString();
    }

    static class RequestDetails {
        private String requestMethod;
        private String requestURI;
        private Map<String, String[]> queryParams;
        private Map<String, String> requestHeaders;
        private String clientIPAddress;
        private String localAddress;
        private String localName;
        private String remoteHost;
        private String sessionID;
        private String userAgent;
        private String preferredLocale;

        public void setRequestMethod(String requestMethod) {
            this.requestMethod = requestMethod;
        }

        public void setRequestURI(String requestURI) {
            this.requestURI = requestURI;
        }

        public void setQueryParams(Map<String, String[]> queryParams) {
            this.queryParams = queryParams;
        }

        public void setRequestHeaders(Map<String, String> requestHeaders) {
            this.requestHeaders = requestHeaders;
        }

        public void setClientIPAddress(String clientIPAddress) {
            this.clientIPAddress = clientIPAddress;
        }

        public void setLocalAddress(String localAddress) {
            this.localAddress = localAddress;
        }

        public void setLocalName(String localName) {
            this.localName = localName;
        }

        public void setRemoteHost(String remoteHost) {
            this.remoteHost = remoteHost;
        }

        public void setSessionID(String sessionID) {
            this.sessionID = sessionID;
        }

        public void setUserAgent(String userAgent) {
            this.userAgent = userAgent;
        }

        public void setPreferredLocale(String preferredLocale) {
            this.preferredLocale = preferredLocale;
        }

        @Override
        public String toString() {
            return "RequestDetails{" +
                    "requestMethod='" + requestMethod + '\'' +
                    ", requestURI='" + requestURI + '\'' +
                    ", queryParams=" + queryParams +
                    ", requestHeaders=" + requestHeaders +
                    ", clientIPAddress='" + clientIPAddress + '\'' +
                    ", localAddress='" + localAddress + '\'' +
                    ", localName='" + localName + '\'' +
                    ", remoteHost='" + remoteHost + '\'' +
                    ", sessionID='" + sessionID + '\'' +
                    ", userAgent='" + userAgent + '\'' +
                    ", preferredLocale='" + preferredLocale + '\'' +
                    '}';
        }

    }

}