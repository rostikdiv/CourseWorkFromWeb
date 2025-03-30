package buccingApp.courseWork.controllers;

import buccingApp.courseWork.dto.LoginRequestDTO;
import buccingApp.courseWork.models.Review;
import buccingApp.courseWork.models.User;
import buccingApp.courseWork.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

    private String generateToken(User user) {
        // Ваша логіка генерації JWT або іншого токена
        return "generated-jwt-token-" + user.getId();
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
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userService.getById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok) // Якщо користувач знайдений
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
    }


    @GetMapping("/getById/{id}/getPass")
    public ResponseEntity<String> getPassById(@PathVariable Long id) {
        return userService.getById(id)
                .map(user -> ResponseEntity.ok(user.getPassword())) // Якщо користувач знайдений
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                .<ResponseEntity<?>>map(ResponseEntity::ok) // Якщо користувач знайдений
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
    }

    @GetMapping("/login/{login}/{password}")
    public User login(@PathVariable String login, @PathVariable String password){
        return userService.authorization(login,password);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(
            @Valid @RequestBody LoginRequestDTO request,
            HttpServletResponse response
    ) {
        // Аутентифікація
        User user = userService.authenticate(request);

        // Генерація токена (приклад, використовуйте вашу логіку)
        String token = generateToken(user);

        // Налаштування HTTP-only куки
        Cookie cookie = new Cookie("auth_token", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Для HTTPS
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60); // Термін дії 7 днів
        response.addCookie(cookie);

        return ResponseEntity.ok(user);
    }

    // UserController.java
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // Інвалідація сесії
        request.getSession().invalidate();

        // Очищення кукі
        Cookie cookie = new Cookie("auth_token", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0); // Видаляємо куку
        response.addCookie(cookie);

        // Додаткове очищення заголовків
        response.setHeader("Authorization", "");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

        System.out.println("Logout successful for session: " + request.getSession().getId());
        return ResponseEntity.ok("Successfully logged out");
    }
//    @GetMapping("/getByEmail/{email}")
//    public Optional<User> getUserByEmail(@PathVariable String email) {
//        return userService.getUserByEmail(email);
//    }
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
