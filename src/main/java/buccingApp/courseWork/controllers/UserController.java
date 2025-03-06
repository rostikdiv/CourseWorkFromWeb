package buccingApp.courseWork.controllers;

import buccingApp.courseWork.models.Review;
import buccingApp.courseWork.models.User;
import buccingApp.courseWork.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
    return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping("/batch")
    public List<User> createUsers(@RequestBody List<User> users){
        return userService.saveUser(users);
    }

    @GetMapping("/getById/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return userService.getById(id);
    }
    @GetMapping("/getByEmail/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        // Знаходимо користувача в базі
        User user = userService.getById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Оновлюємо поля, якщо передані нові значення
        if (updatedUser.getFirstName() != null) user.setFirstName(updatedUser.getFirstName());
        if (updatedUser.getLastName() != null) user.setLastName(updatedUser.getLastName());
        if (updatedUser.getEmail() != null) user.setEmail(updatedUser.getEmail());
        if (updatedUser.getPhoneNumber() != null) user.setPhoneNumber(updatedUser.getPhoneNumber());
        if (updatedUser.getLogin() != null) user.setLogin(updatedUser.getLogin());
        if (updatedUser.getPassword() != null) user.setPassword(updatedUser.getPassword());

        // Зберігаємо оновлені дані в базі
        User savedUser = userService.saveUser(user);

        return ResponseEntity.ok(savedUser);
    }
    @PostMapping("/delete/user")
    public String delete(@RequestBody User user){
        return userService.delete(user);
    }
    @GetMapping("delete/byId/{id}")
    public String deleteById(@PathVariable Long id){
        return userService.deleteById(id);
    }
    @GetMapping("/delete/all")
    public String deleteAll(){
        return userService.deleteAll();
    }
    @PostMapping("delete/byId")
    public String deleteAllById(@RequestBody List<Long> ids){
        return userService.deleteAllById(ids);
    }
    @PostMapping("/delete/users")
    public String deleteAll(@RequestBody List<User> users){
        return userService.deleteAll(users);
    }


}
