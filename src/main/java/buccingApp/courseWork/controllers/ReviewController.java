package buccingApp.courseWork.controllers;

import buccingApp.courseWork.models.Review;
import buccingApp.courseWork.services.ReviewService;
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
    @PostMapping
    public Review addReview(@RequestBody Review review){
        return reviewService.addReview(review);
    }
    @GetMapping("/{id}")
    public Optional<Review> getReviewById(@PathVariable Long id){
        return reviewService.getById(id);
    }
}
