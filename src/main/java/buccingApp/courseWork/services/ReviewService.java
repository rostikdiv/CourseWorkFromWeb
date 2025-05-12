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
    private final UserService userService;

    public ReviewService(
            ReviewRepository reviewRepository,
            HouseForRentRepository houseForRentRepository,
            UserRepository userRepository,
            UserService userService
    ) {
        this.reviewRepository = reviewRepository;
        this.houseForRentRepository = houseForRentRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByHouseId(Long houseId) {
        return reviewRepository.findByHouseForRentId(houseId);
    }

    public List<Review> getReviewsByAuthorId(Long authorId) {
        return reviewRepository.findByAuthorId(authorId);
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

    public Review updateReview(Long id, Review updatedReview, String authHeader) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        Long userId = extractUserIdFromToken(authHeader);
        if (!review.getAuthorId().equals(userId)) {
            throw new RuntimeException("You can only edit your own reviews");
        }
        if (updatedReview.getComment() != null) {
            review.setComment(updatedReview.getComment());
        }
        if (updatedReview.getRating() != 0) {
            review.setRating(updatedReview.getRating());
        }
        return reviewRepository.save(review);
    }

    public String delete(Review review) {
        reviewRepository.delete(review);
        return "Review: " + review.toString() + " has been deleted";
    }

    public String deleteById(Long id, String authHeader) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        Long userId = extractUserIdFromToken(authHeader);
        if (!review.getAuthorId().equals(userId)) {
            throw new RuntimeException("You can only delete your own reviews");
        }
        reviewRepository.deleteById(id);
        return "Review with id: " + id + " has been deleted";
    }

    public String deleteAll() {
        reviewRepository.deleteAll();
        return "All reviews have been deleted";
    }

    private Long extractUserIdFromToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid or missing Authorization header");
        }
        String token = authHeader.substring(7);
        boolean isValid = userService.verifyToken(token);
        if (!isValid) {
            throw new RuntimeException("Invalid token");
        }
        try {
            String idStr = token.substring("generated-jwt-token-".length());
            return Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid token format");
        }
    }
}