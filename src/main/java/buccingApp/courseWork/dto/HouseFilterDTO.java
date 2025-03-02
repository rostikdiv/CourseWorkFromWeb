package buccingApp.courseWork.dto;

import lombok.Data;

@Data
public class HouseFilterDTO {
    private String city;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer minRooms;
    private Double minArea;
    private Boolean hasWifi;
    private Boolean hasParking;
    private Boolean hasPool;
    private String keyword;

}
