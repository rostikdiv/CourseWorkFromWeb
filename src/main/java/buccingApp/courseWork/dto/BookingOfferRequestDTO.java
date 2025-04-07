package buccingApp.courseWork.dto;

import java.time.LocalDate;

public class BookingOfferRequestDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private Long houseId; // Додано поле

    // Геттери та сеттери
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public Long getHouseId() { return houseId; }
    public void setHouseId(Long houseId) { this.houseId = houseId; }

}
