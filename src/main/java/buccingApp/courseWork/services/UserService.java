package buccingApp.courseWork.services;

import buccingApp.courseWork.dto.LoginRequestDTO;
import buccingApp.courseWork.models.User;
import buccingApp.courseWork.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> saveUser(List<User> users) {
        return userRepository.saveAll(users);
    }
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getById(Long id){
        return userRepository.findById(id);
    }
    public User authorization(String login, String password) {
        Optional<User> optionalUser = userRepository.findByLogin(login);

        System.out.println("login: "+login+"password: "+password);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        // Порівнюємо значення, а не посилання
        if (user.getPassword().equals(password.trim())) {
            user.setPassword(null);
            return user;
        } else {
            throw new RuntimeException("Incorrect password");
        }
    }

    // src/main/java/buccingApp/courseWork/services/UserService.java
    public User authenticate(LoginRequestDTO request) {
        User user = userRepository.findByLogin(request.getLogin())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }


    public String delete(User user){
        userRepository.delete(user);
        return "user: "+user.toString()+" has deleted";
    }
    public String deleteById(Long id){
        userRepository.deleteById(id);
        return "user with id:"+id+" has deleted";
    }
    public String deleteAllById(List<Long> ids){
        userRepository.deleteAllById(ids);
        return "users with ids: "+ ids.toString()+" has deleted";
    }
    public String deleteAll(List<User> users){
        userRepository.deleteAll(users);
        return "users: "+users.toString()+"has deleted";
    }
    public String deleteAll(){
        userRepository.deleteAll();
        return "all users has deleted";
    }


}
