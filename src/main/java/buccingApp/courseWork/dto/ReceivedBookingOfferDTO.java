package buccingApp.courseWork.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ReceivedBookingOfferDTO {
    private Long id;
    private String authorLogin;
    private String authorPhoneNumber;
    private String houseTitle;
    private LocalDate offerFrom;
    private LocalDate offerTo;

    public ReceivedBookingOfferDTO(Long id, String authorLogin, String authorPhoneNumber, String houseTitle, LocalDate offerFrom, LocalDate offerTo) {
        this.id = id;
        this.authorLogin = authorLogin;
        this.authorPhoneNumber = authorPhoneNumber;
        this.houseTitle = houseTitle;
        this.offerFrom = offerFrom;
        this.offerTo = offerTo;
    }

    public ReceivedBookingOfferDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorLogin() {
        return authorLogin;
    }

    public void setAuthorLogin(String authorLogin) {
        this.authorLogin = authorLogin;
    }

    public String getAuthorPhoneNumber() {
        return authorPhoneNumber;
    }

    public void setAuthorPhoneNumber(String authorPhoneNumber) {
        this.authorPhoneNumber = authorPhoneNumber;
    }

    public String getHouseTitle() {
        return houseTitle;
    }

    public void setHouseTitle(String houseTitle) {
        this.houseTitle = houseTitle;
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
}