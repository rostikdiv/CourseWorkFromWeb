package buccingApp.courseWork.controllers;

import buccingApp.courseWork.dto.PhotoRequestDTO;
import buccingApp.courseWork.models.Photo;
import buccingApp.courseWork.services.PhotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {
    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("/add/{houseId}")
    public Photo addPhoto(
            @PathVariable Long houseId,
            @RequestParam String imageUrl // Очікує параметр у URL
    ) {
        return photoService.addPhotoToHouse(houseId, imageUrl);
    }
    @PostMapping("/{houseId}")
    public ResponseEntity<List<Photo>> addPhoto(
            @PathVariable Long houseId, // ID оголошення
            @RequestBody PhotoRequestDTO photoRequest // JSON з тіла запиту
    ) {
        List<Photo> savedPhotos = photoService.addPhotosToHouse(houseId, photoRequest.getImageUrls());
        return ResponseEntity.ok(savedPhotos);

    }

}