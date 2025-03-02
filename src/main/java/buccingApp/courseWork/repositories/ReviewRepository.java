package buccingApp.courseWork.repositories;

import buccingApp.courseWork.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
