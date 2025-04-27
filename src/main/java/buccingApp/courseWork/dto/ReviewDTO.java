package buccingApp.courseWork.dto;

import java.time.LocalDateTime;

public class ReviewDTO {
    private Long id;
    private String authorLogin;
    private String comment;
    private int rating;
    private LocalDateTime createdAt;
    private Long houseForRentId;

    public ReviewDTO(Long id, String authorLogin, String comment, int rating, LocalDateTime createdAt, Long houseForRentId) {
        this.id = id;
        this.authorLogin = authorLogin;
        this.comment = comment;
        this.rating = rating;
        this.createdAt = createdAt;
        this.houseForRentId = houseForRentId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAuthorLogin() { return authorLogin; }
    public void setAuthorLogin(String authorLogin) { this.authorLogin = authorLogin; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Long getHouseForRentId() { return houseForRentId; }
    public void setHouseForRentId(Long houseForRentId) { this.houseForRentId = houseForRentId; }
}