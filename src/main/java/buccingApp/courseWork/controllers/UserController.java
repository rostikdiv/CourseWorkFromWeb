package buccingApp.courseWork.controllers;

import buccingApp.courseWork.models.Review;
import buccingApp.courseWork.models.User;
import buccingApp.courseWork.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book-site")
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
        return userService.createUser(user);
    }

    @PostMapping("/batch")
    public List<User> createUsers(@RequestBody List<User> users){
        return userService.createUsers(users);
    }

    @GetMapping("/getById/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return userService.getById(id);
    }
    @GetMapping("/getByEmail/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }
    @PostMapping("/delete/user")
    public String delete(@RequestBody User user){
        return userService.delete(user);
    }
    @GetMapping("delete/byId/{id}")
    public String deleteById(@PathVariable Long id){
        return userService.deleteById(id);
    }
    @PostMapping("delete/byId")
    public String deleteAllById(@RequestBody List<Long> ids){
        return userService.deleteAllById(ids);
    }
    @PostMapping("/delete/users")
    public String deleteAll(@RequestBody List<User> users){
        return userService.deleteAll(users);
    }
    @GetMapping("/delete/all")
    public String deleteAll(){
        return userService.deleteAll();
    }

}
