package com.ty.app.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppHealthController {

    @GetMapping("/health")
    public AppStatus health() {
        AppStatus status = new AppStatus();
        status.setModule("app");
        status.setStatus("ok");
        status.setMessage("app module started");
        return status;
    }

    @Data
    public static class AppStatus {
        private String module;
        private String status;
        private String message;
    }
}
