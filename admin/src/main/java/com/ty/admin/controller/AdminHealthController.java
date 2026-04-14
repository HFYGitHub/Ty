package com.ty.admin.controller;

import com.ty.admin.service.AdminStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminHealthController {

    private final AdminStatusService adminStatusService;

    @GetMapping("/health")
    public Map<String, Object> health() {
        return adminStatusService.status();
    }
}
