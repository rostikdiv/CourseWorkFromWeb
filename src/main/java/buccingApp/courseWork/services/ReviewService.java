package buccingApp.courseWork.services;

import buccingApp.courseWork.dto.ReviewDTO;
import buccingApp.courseWork.models.HouseForRent;
import buccingApp.courseWork.models.Review;
import buccingApp.courseWork.models.User;
import buccingApp.courseWork.repositories.HouseForRentRepository;
import buccingApp.courseWork.repositories.ReviewRepository;
import buccingApp.courseWork.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final HouseForRentRepository houseForRentRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, HouseForRentRepository houseForRentRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.houseForRentRepository = houseForRentRepository;
        this.userRepository = userRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByHouseId(Long houseId) {
        return reviewRepository.findByHouseForRentId(houseId);
    }

    public List<ReviewDTO> getReviewsByHouseIdWithLogin(Long houseId) {
        List<Review> reviews = reviewRepository.findByHouseForRentId(houseId);
        return reviews.stream().map(review -> {
            User author = userRepository.findById(review.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("User not found for authorId: " + review.getAuthorId()));
            return new ReviewDTO(
                    review.getId(),
                    author.getLogin(),
                    review.getComment(),
                    review.getRating(),
                    review.getCreatedAt(),
                    review.getHouseForRent().getId()
            );
        }).collect(Collectors.toList());
    }

    public Review saveReview(Review review) {
        if (review.getCreatedAt() == null) {
            review.setCreatedAt(LocalDateTime.now());
        }
        HouseForRent house = houseForRentRepository.findById(review.getHouseForRent().getId())
                .orElseThrow(() -> new RuntimeException("House not found for id: " + review.getHouseForRent().getId()));
        review.setHouseForRent(house);
        return reviewRepository.save(review);
    }

    public HouseForRent saveReview(Review review, Long houseId) {
        if (review.getCreatedAt() == null) {
            review.setCreatedAt(LocalDateTime.now());
        }
        HouseForRent house = houseForRentRepository.findById(houseId)
                .orElseThrow(() -> new RuntimeException("House not found for id: " + houseId));
        review.setHouseForRent(house);
        reviewRepository.save(review);
        return house;
    }

    public Optional<Review> getById(Long id) {
        return reviewRepository.findById(id);
    }

    public String delete(Review review) {
        reviewRepository.delete(review);
        return "Review: " + review.toString() + " has been deleted";
    }

    public String deleteById(Long id) {
        reviewRepository.deleteById(id);
        return "Review with id: " + id + " has been deleted";
    }

    public String deleteAll() {
        reviewRepository.deleteAll();
        return "All reviews have been deleted";
    }
}