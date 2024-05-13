package com.gangulwar.peekip;

import java.util.Map;

public class RequestDetails {
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
