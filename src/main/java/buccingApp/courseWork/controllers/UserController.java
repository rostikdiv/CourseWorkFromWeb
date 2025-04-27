package buccingApp.courseWork.controllers;

import buccingApp.courseWork.dto.LoginDTO;
import buccingApp.courseWork.models.User;
import buccingApp.courseWork.dto.UserDTO;

import buccingApp.courseWork.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(
            @Valid @RequestBody LoginDTO request,
            HttpServletResponse response
    ) {
        User user = userService.authenticate(request);
        String token = generateToken(user);
        Cookie cookie = new Cookie("auth_token", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
        System.out.println("Login successful, token: " + token); // Логування
        return ResponseEntity.ok(new UserDTO(user));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("Invalid or missing Authorization header"); // Логування
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        String token = authHeader.substring(7);
        boolean isValid = userService.verifyToken(token);
        if (!isValid) {
            System.out.println("Token invalid: " + token); // Логування
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        try {
            String idStr = token.substring("generated-jwt-token-".length());
            Long id = Long.parseLong(idStr);
            return userService.getById(id)
                    .map(user -> {
                        System.out.println("User found for ID: " + id); // Логування
                        return ResponseEntity.ok(new UserDTO(user));
                    })
                    .orElseGet(() -> {
                        System.out.println("User not found for ID: " + id); // Логування
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                    });
        } catch (NumberFormatException e) {
            System.out.println("Invalid token format: " + token); // Логування
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/verify-token")
    public ResponseEntity<Boolean> verifyToken(@RequestBody String token) {
        System.out.println("Verify token request received: " + token); // Логування
        try {
            boolean isValid = userService.verifyToken(token);
            System.out.println("Token valid: " + isValid); // Логування
            return ResponseEntity.ok(isValid);
        } catch (Exception e) {
            System.out.println("Error verifying token: " + e.getMessage()); // Логування
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user) {
        User savedUser = userService.saveUser(user);
        System.out.println("User created, ID: " + savedUser.getId()); // Логування
        return ResponseEntity.ok(new UserDTO(savedUser));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("auth_token", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        System.out.println("Logout successful"); // Логування
        return ResponseEntity.ok().build();
    }

    private String generateToken(User user) {
        return "generated-jwt-token-" + user.getId();
    }
}