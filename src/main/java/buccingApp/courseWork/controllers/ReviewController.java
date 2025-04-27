package buccingApp.courseWork.controllers;

import buccingApp.courseWork.dto.ReviewDTO;
import buccingApp.courseWork.models.HouseForRent;
import buccingApp.courseWork.models.Review;
import buccingApp.courseWork.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable Long id) {
        return reviewService.getById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found"));
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        System.out.println("Adding review: " + review); // Логування
        Review savedReview = reviewService.saveReview(review);
        return ResponseEntity.ok(savedReview);
    }

    @PostMapping("/toReview/{id}")
    public ResponseEntity<HouseForRent> updateHouseForRent(@RequestBody Review review, @PathVariable Long id) {
        HouseForRent house = reviewService.saveReview(review, id);
        return ResponseEntity.ok(house);
    }

    @GetMapping("/byHouse/{houseId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByHouseId(@PathVariable Long houseId) {
        List<ReviewDTO> reviews = reviewService.getReviewsByHouseIdWithLogin(houseId);
        System.out.println("Reviews for houseId " + houseId + ": " + reviews); // Логування
        return ResponseEntity.ok(reviews);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review updatedReview) {
        Review review = reviewService.getById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        if (updatedReview.getComment() != null) {
            review.setComment(updatedReview.getComment());
        }
        if (updatedReview.getRating() != 0) {
            review.setRating(updatedReview.getRating());
        }
        Review savedReview = reviewService.saveReview(review);
        return ResponseEntity.ok(savedReview);
    }

    @PostMapping("/delete/review")
    public String delete(@RequestBody Review review) {
        return reviewService.delete(review);
    }

    @GetMapping("/delete/byId/{id}")
    public String deleteById(@PathVariable Long id) {
        return reviewService.deleteById(id);
    }

    @GetMapping("/delete/all")
    public String deleteAll() {
        return reviewService.deleteAll();
    }
}