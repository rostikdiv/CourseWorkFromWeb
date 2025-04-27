package buccingApp.courseWork.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MyBookingOfferDTO {
    private Long id;
    private String houseTitle;
    private LocalDate offerFrom;
    private LocalDate offerTo;


    public MyBookingOfferDTO(Long id, String houseTitle, LocalDate offerFrom, LocalDate offerTo) {
        this.id = id;
        this.houseTitle = houseTitle;
        this.offerFrom = offerFrom;
        this.offerTo = offerTo;
    }
    public MyBookingOfferDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOfferTo() {
        return offerTo;
    }

    public void setOfferTo(LocalDate offerTo) {
        this.offerTo = offerTo;
    }

    public LocalDate getOfferFrom() {
        return offerFrom;
    }

    public void setOfferFrom(LocalDate offerFrom) {
        this.offerFrom = offerFrom;
    }

    public String getHouseTitle() {
        return houseTitle;
    }

    public void setHouseTitle(String houseTitle) {
        this.houseTitle = houseTitle;
    }
}
