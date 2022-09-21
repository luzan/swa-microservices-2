
package com.example.orderservice.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyRequestInterceptor implements RequestInterceptor {

    @Autowired
    private HttpServletRequest httpServletRequest;


    @Override
    public void apply(RequestTemplate requestTemplate) {
        String jwt =  httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        requestTemplate.header(HttpHeaders.AUTHORIZATION, jwt);
    }
}