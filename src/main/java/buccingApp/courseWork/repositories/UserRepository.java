package buccingApp.courseWork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import buccingApp.courseWork.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
