package com.flightsearch.proxy.controller;

import com.flightsearch.proxy.service.Properties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class VersionController {
    private final Properties properties;
    public static Map<String, String> iatas;

    public VersionController(Properties properties) {
        this.properties = properties;
    }

    @GetMapping("/version")
    @ResponseBody
    public String getVersion() {
        return this.properties.getVersion();
    }
}
