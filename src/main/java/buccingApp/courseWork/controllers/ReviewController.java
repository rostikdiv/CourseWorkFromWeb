package buccingApp.courseWork.controllers;

import buccingApp.courseWork.models.HouseForRent;
import buccingApp.courseWork.models.Review;
import buccingApp.courseWork.models.User;
import buccingApp.courseWork.services.HouseForRentService;
import buccingApp.courseWork.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService =reviewService;
    }
    @GetMapping
    public List<Review> getAllReviews(){
        return reviewService.getAllReviews();
    }
    @GetMapping("/{id}")
    public Optional<Review> getReviewById(@PathVariable Long id){
        return reviewService.getById(id);
    }
    @PostMapping
    public Review addReview(@RequestBody Review review){
        return reviewService.saveReview(review);
    }
    @PostMapping("/toReview/{id}")
    public HouseForRent updateHouseForRent(@RequestBody Review review, @PathVariable Long id){
        return reviewService.saveReview(review, id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Review> updateUser(@PathVariable Long id, @RequestBody Review updatedReview) {
        // Знаходимо користувача в базі
        Review review = reviewService.getById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if (updatedReview.getComment() != null){ review.setComment(updatedReview.getComment());}
        if (updatedReview.getRating() != 0){ review.setRating(updatedReview.getRating());}


        // Зберігаємо оновлені дані в базі
        Review savedReview = reviewService.saveReview(review);

        return ResponseEntity.ok(savedReview);
    }

    @PostMapping("/delete/review")
    public String delete(@RequestBody Review review){
        return reviewService.delete(review);
    }
    @GetMapping("delete/byId/{id}")
    public String deleteById(@PathVariable Long id){
        return reviewService.deleteById(id);
    }
    @GetMapping("/delete/all")
    public String deleteAll(){
        return reviewService.deleteAll();
    }
}
