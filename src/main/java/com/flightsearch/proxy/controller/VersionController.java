package com.flightsearch.proxy.controller;

import com.flightsearch.proxy.service.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/version")
public class VersionController {
    private final PropertiesService properties;

    @Autowired
    public VersionController(PropertiesService properties) {
        this.properties = properties;
    }

    @GetMapping("")
    @ResponseBody
    public String getVersion() {
        return this.properties.getVersion();
    }
}
