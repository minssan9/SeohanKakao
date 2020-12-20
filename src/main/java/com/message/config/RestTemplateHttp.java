package com.message.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTemplateHttp extends RestTemplate {
    @Autowired RestTemplateBuilder restTemplateBuilder;

    public RestTemplate getRestTemplate(){
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();			// RestTemplate 에 MessageConverter 세팅
        converters.add(new FormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter());
        return  restTemplateBuilder.additionalMessageConverters(converters).build();
    }


}
