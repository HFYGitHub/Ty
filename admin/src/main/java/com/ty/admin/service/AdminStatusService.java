package com.ty.admin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class AdminStatusService {

    @Value("${ty.organ-name}")
    private String organName;

    public Map<String, Object> status() {
        log.debug("Admin health endpoint invoked");
        Map<String, Object> result = new HashMap<>();
        result.put("module", "admin");
        result.put("status", "ok");
        result.put("message", "admin module started");
        result.put("organName", organName);
        return result;
    }
}
