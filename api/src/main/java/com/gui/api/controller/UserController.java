package com.gui.api.controller;

import com.gui.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int page_size,
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) Boolean is_active) {

        if (page_size > 50 || page_size < 1) {
            return ResponseEntity.badRequest().body(Map.of("error", "page_size deve ser entre 1 e 50"));
        }
        if (page < 1) {
            return ResponseEntity.badRequest().body(Map.of("error", "page deve ser >= 1"));
        }

        return ResponseEntity.ok(service.getUsers(page, page_size, q, role, is_active));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        var user = service.getUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Usuário não encontrado"));
        }
        return ResponseEntity.ok(user);
    }
}
