package buccingApp.courseWork.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "offers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("lessorId")
    @Column(nullable = false)
    private Long lessorId;

    @JsonProperty("offerFrom")
    @Column(nullable = false)
    private LocalDate offerFrom;

    @JsonProperty("offerTo")
    @Column(nullable = false)
    private LocalDate offerTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "houseOffer_id")
    @JsonBackReference("house-offer")
    private HouseForRent houseOffer;

    // Додаємо геттер для houseForRentId, який повертає ID будинку
    @JsonProperty("houseForRentId")
    public Long getHouseForRentId() {
        return houseOffer != null ? houseOffer.getId() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLessorId() {
        return lessorId;
    }

    public void setLessorId(Long lessorId) {
        this.lessorId = lessorId;
    }

    public LocalDate getOfferFrom() {
        return offerFrom;
    }

    public void setOfferFrom(LocalDate offerFrom) {
        this.offerFrom = offerFrom;
    }

    public LocalDate getOfferTo() {
        return offerTo;
    }

    public void setOfferTo(LocalDate offerTo) {
        this.offerTo = offerTo;
    }

    public HouseForRent getHouseOffer() {
        return houseOffer;
    }

    public void setHouseOffer(HouseForRent houseOffer) {
        this.houseOffer = houseOffer;
    }
}