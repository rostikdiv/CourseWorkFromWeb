package buccingApp.courseWork.repositories;

import buccingApp.courseWork.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    public List<Review> findByHouseForRentId(Long houseId);
}
