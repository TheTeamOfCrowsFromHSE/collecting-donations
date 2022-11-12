package com._2dmes_.collectingdonations.components;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HHTPComponent {
    static private String url = "https://admin.netmonet.co/api/external/v1/transaction/restaurant/overall/comments";
    static private String token = "Token ZDg1MmY4NjgtZWRjNy00YzRjLWEzY2MtMjhmNmY1NzMwNzdhOmhzSkZEaTU4b1JjOG1QMnlhWmM2a20wWmlVbWNZdlFIZFM2YVc1MHlDUw==";

    static private HttpHeaders headers;
    static private HttpEntity request;
    static private ResponseEntity<String> response;

    public static void createHeaders() {
        headers = new HttpHeaders();
        headers.add("Authorization", token);
    }

    public static void createRequest() {
        request = new HttpEntity(headers);
    }

    public static ResponseEntity<String> getResponse() {
        response = new RestTemplate().exchange(url, HttpMethod.GET, request, String.class);
        return response;
    }
}
