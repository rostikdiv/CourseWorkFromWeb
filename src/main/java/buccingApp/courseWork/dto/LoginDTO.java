package buccingApp.courseWork.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginDTO {

    @NotBlank(message = "Логін не може бути порожнім")
    private String login;

    @NotBlank(message = "Пароль не може бути порожнім")
    private String password;

    // Геттери і сеттери
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}