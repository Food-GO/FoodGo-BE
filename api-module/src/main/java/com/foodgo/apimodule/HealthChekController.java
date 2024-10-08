package com.foodgo.apimodule;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthChekController {

    @GetMapping(value = "/health-check")
    public ResponseEntity<String> healthCheck() {

        return ResponseEntity.ok("hi");
    }
}
