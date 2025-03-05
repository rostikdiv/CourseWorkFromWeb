package buccingApp.courseWork.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")  // Зазвичай таблиця "users", бо "user" зарезервоване слово в SQL
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference("user-house")
    private List<HouseForRent> housesForRentList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL,orphanRemoval = true)
//    @JsonManagedReference("user-review")
//    private List<Long> ownReviewsId = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public List<HouseForRent> getHousesForRentList() {
        return housesForRentList;
    }

    public void setHousesForRentList(List<HouseForRent> housesForRentList) {
        this.housesForRentList = housesForRentList;
    }

//    public List<Long> getOwnReviewsId() {
//        return ownReviewsId;
//    }
//
//    public void setOwnReviewsId(List<Long> ownReviewsId) {
//        this.ownReviewsId = ownReviewsId;
//    }
}
