package buccingApp.courseWork.dto;

import java.util.List;

public class PhotoRequestDTO {
    private List<String> imageUrls; // Поле для URL фотографії

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
