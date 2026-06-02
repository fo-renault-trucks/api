package com.rt_fo.api.user.controller;

import com.rt_fo.api.user.dto.UserDto;
import com.rt_fo.api.user.dto.request.UserCreationRequest;
import com.rt_fo.api.user.dto.request.UserUpdateRequest;
import com.rt_fo.api.user.entity.User;
import com.rt_fo.api.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(
                userService.getUsers()
                        .stream()
                        .map(UserDto::fromEntity)
                        .toList()
        );
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserCreationRequest request) {
        User user = userService.createUser(request);

        return ResponseEntity.created(URI.create("/api/users/%d".formatted(user.getId())))
                .body(UserDto.fromEntity(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateRequest request) {
        User user = userService.updateUser(id, request);

        return ResponseEntity.ok(UserDto.fromEntity(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent()
                .build();
    }
}
