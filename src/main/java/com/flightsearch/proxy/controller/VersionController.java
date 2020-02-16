package com.flightsearch.proxy.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightsearch.proxy.model.Airport;
import com.flightsearch.proxy.service.Properties;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Controller
public class VersionController {
    @Autowired
    private Properties properties;
    public static Map<String, String> iatas;

    @GetMapping("/version")
    @ResponseBody
    public String getVersion() {
        return this.properties.getVersion();
    }
}
