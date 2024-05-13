package com.gangulwar.peekip;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SimpleClass {

    public static String req(HttpServletRequest request){

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
}
