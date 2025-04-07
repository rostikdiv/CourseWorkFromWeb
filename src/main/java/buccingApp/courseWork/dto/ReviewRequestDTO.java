package buccingApp.courseWork.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDTO {
    private String comment;
    private Long houseId; // Додано поле

    // Геттери та сеттери
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public Long getHouseId() { return houseId; }
    public void setHouseId(Long houseId) { this.houseId = houseId; }
}
