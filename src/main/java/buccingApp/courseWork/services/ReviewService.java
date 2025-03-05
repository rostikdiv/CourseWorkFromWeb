package buccingApp.courseWork.services;

import buccingApp.courseWork.models.Review;
import buccingApp.courseWork.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository=reviewRepository;
    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }
    public Optional<Review> getById(Long id){
        return reviewRepository.findById(id);
    }
}
