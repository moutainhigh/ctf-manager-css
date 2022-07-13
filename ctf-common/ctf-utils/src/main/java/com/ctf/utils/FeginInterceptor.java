package com.ctf.utils;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Slf4j
public class FeginInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest request = WebUtil.getRequest();
        if(request!=null){
//            log.info("token=={}==={}",request.getRequestURI(),request.getHeader("token"));
            requestTemplate.header("token",request.getHeader("token"));
        }
    }

    private Map<String, String> getHeaders() {
        HttpServletRequest request = WebUtil.getRequest();
        Map<String, String> map = new LinkedHashMap<>();
        if(request!=null){
            Enumeration<String> enumeration = request.getHeaderNames();
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                String value = request.getHeader(key);
                // 跳过 content-length
                if (key.equals("content-length")){
                    continue;
                }
                map.put(key, value);
            }
        }

        return map;
    }
}
