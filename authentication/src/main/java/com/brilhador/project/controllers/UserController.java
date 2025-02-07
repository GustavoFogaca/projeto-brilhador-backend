package com.brilhador.project.controllers;

import java.util.UUID;

import com.brilhador.project.models.dto.UserResponse;
import com.brilhador.project.models.exceptions.NotFound;
import com.brilhador.project.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "Users")
@RestController @RequestMapping(path = "users")
public class UserController {
    
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        try {
            UserResponse user = userService.getUser(id).toUserResponse();
            return ResponseEntity.ok().body(user);
        } catch (NotFound e) {
            return ResponseEntity.notFound().build();
        }
    }
}
