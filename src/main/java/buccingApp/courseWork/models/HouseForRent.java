package buccingApp.courseWork.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "housesForRent")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@CrossOrigin(origins = "*") // Якщо ти надсилаєш запити з фронтенду
public class HouseForRent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Double area;

    @Column(nullable = false)
    private Integer rooms;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private Boolean hasWifi;

    @Column(nullable = false)
    private Boolean hasParking;

    @Column(nullable = false)
    private Boolean hasPool;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="owner_id", nullable = false)
    @JsonBackReference("user-house")
    private User owner;

    @JsonProperty("houseOwnerId")  // Це гарантує, що поле буде в JSON
    public Long getHouseOwnerId() {
        return owner != null ? owner.getId() : null;
    }

//    private Long houseOwnerId = owner != null ? owner.getId() : null;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("house-photo")
    private List<Photo> photos = new ArrayList<>();

    @OneToMany(mappedBy = "houseForRent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("house-review")
    private List<Review> reviewsTo = new ArrayList<>();

    @OneToMany(mappedBy = "houseOffer", cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonManagedReference("house-offer")
    private List<BookingOffer> bookingOffers =new ArrayList<>();



    // Додаткові методи для керування фотографіями
    public void addPhoto(Photo photo) {
        photos.add(photo);
        photo.setHouse(this);
    }

    public void removePhoto(Photo photo) {
        photos.remove(photo);
        photo.setHouse(null);
    }

    public void addReview(Review review){
        reviewsTo.add(review);
        review.setHouseForRent(this);
    }
    public void removeReview(Review review){
        reviewsTo.remove(review);
        review.setHouseForRent(null);
    }

    public void addHouseOffer(BookingOffer bookingOffer){
        bookingOffers.add(bookingOffer);
        bookingOffer.setHouseOffer(this);
    }

    public void removeOffer(BookingOffer bookingOffer){
        bookingOffers.remove(bookingOffer);
        bookingOffer.setHouseOffer(null);
    }

    public void setPhotos(List<Photo> photos) {
        this.photos.clear();
        if (photos != null) {
            this.photos.addAll(photos);
            photos.forEach(photo -> photo.setHouse(this));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getHasParking() {
        return hasParking;
    }

    public void setHasParking(Boolean hasParking) {
        this.hasParking = hasParking;
    }

    public Boolean getHasWifi() {
        return hasWifi;
    }

    public void setHasWifi(Boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public Boolean getHasPool() {
        return hasPool;
    }

    public void setHasPool(Boolean hasPool) {
        this.hasPool = hasPool;
    }

//    @JsonProperty("ownerId")
    public User getOwner() {
        return owner ;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

//    public void setPhotos(List<Photo> photos) {
//        this.photos = photos;
//    }

    public List<Review> getReviewsTo() {
        return reviewsTo;
    }

    public void setReviewsTo(List<Review> reviewsTo) {
        this.reviewsTo = reviewsTo;
    }

    public List<BookingOffer> getBookingOffers() {
        return bookingOffers;
    }

    public void setBookingOffers(List<BookingOffer> bookingOffers) {
        this.bookingOffers = bookingOffers;
    }

//    public Long getHouseOwnerId() {
//        return houseOwnerId;
//    }
//
//    public void setHouseOwnerId(Long ownerId) {
//        this.houseOwnerId = ownerId;
//    }
}
