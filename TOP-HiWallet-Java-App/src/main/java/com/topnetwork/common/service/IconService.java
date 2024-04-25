package com.topnetwork.common.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("iconService")
public class IconService {

    @Value("${pic.base.url}")
    private String picBaseUrl;

    public String buildURL(String key) {
        return picBaseUrl + key;
    }
}
