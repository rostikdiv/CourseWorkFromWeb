package buccingApp.courseWork.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "housesForRent")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseForRent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private double area;

    @Column(nullable = false)
    private int rooms;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String city;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name ="owner_id", nullable = false)
    @JsonBackReference("user-house")
    private User owner;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("house-photo")
    private List<Photo> photos = new ArrayList<>();

    @OneToMany(mappedBy = "houseForRent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("house-review")
    private List<Review> reviewsTo = new ArrayList<>();

    // Додаткові методи для керування фотографіями
    public void addPhoto(Photo photo) {
        photos.add(photo);
        photo.setHouse(this);
    }

    public void removePhoto(Photo photo) {
        photos.remove(photo);
        photo.setHouse(null);
    }

    // Зручності
    private boolean hasWifi = false;
    private boolean hasParking = false;
    private boolean hasPool = false;

    public Long getOwnerId() {
        return owner != null ? owner.getId() : null;
    }

    public boolean isHasPool() {
        return hasPool;
    }

    public void setHasPool(boolean hasPool) {
        this.hasPool = hasPool;
    }

    public boolean isHasParking() {
        return hasParking;
    }

    public void setHasParking(boolean hasParking) {
        this.hasParking = hasParking;
    }

    public boolean isHasWifi() {
        return hasWifi;
    }

    public void setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public List<Review> getReviewsTo() {
        return reviewsTo;
    }

    public void setReviewsTo(List<Review> reviewsTo) {
        this.reviewsTo = reviewsTo;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
