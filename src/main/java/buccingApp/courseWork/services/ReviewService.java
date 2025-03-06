package buccingApp.courseWork.services;

import buccingApp.courseWork.models.Review;
import buccingApp.courseWork.models.User;
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

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }
    public List<Review> saveReview(List<Review> reviews) {
        return reviewRepository.saveAll(reviews);
    }
    public Optional<Review> getById(Long id){
        return reviewRepository.findById(id);
    }

    public String delete(Review review){
        reviewRepository.delete(review);
        return "review: "+review.toString()+" has deleted";
    }
    public String deleteById(Long id){
        reviewRepository.deleteById(id);
        return "review with id:"+id+" has deleted";
    }
    public String deleteAll(){
        reviewRepository.deleteAll();
        return "all reviews has deleted";
    }
}
