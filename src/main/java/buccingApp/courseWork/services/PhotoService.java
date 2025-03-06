package buccingApp.courseWork.services;

import buccingApp.courseWork.models.HouseForRent;
import buccingApp.courseWork.models.Photo;
import buccingApp.courseWork.models.Review;
import buccingApp.courseWork.repositories.PhotoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final HouseForRentService houseService;

    public PhotoService(PhotoRepository photoRepository, HouseForRentService houseService) {
        this.photoRepository = photoRepository;
        this.houseService = houseService;
    }
    public Optional<Photo> getPhotoById(Long id){
        return photoRepository.findById(id);
    }
    public List<Photo> getAllPhotos(){
        return photoRepository.findAll();
    }

    public Photo savePhoto(Photo photo){
        return photoRepository.save(photo);
    }

    public Photo addPhotoToHouse(Long houseId, String imageUrl) {
        HouseForRent house = houseService.getById(houseId).orElseThrow();
        Photo photo = new Photo();
        photo.setImageUrl(imageUrl);
        photo.setHouse(house);
        return photoRepository.save(photo);
    }

    public List<Photo> addPhotosToHouse(Long houseId, List<String> imageUrls) {
        HouseForRent house = houseService.getById(houseId)
                .orElseThrow(() -> new RuntimeException("Оголошення не знайдено"));

        List<Photo> photos = new ArrayList<>();
        for (String imageUrl : imageUrls) {
            Photo photo = new Photo();
            photo.setImageUrl(imageUrl);
            photo.setHouse(house);
            photos.add(photo);
        }

        return photoRepository.saveAll(photos); // Зберігаємо всі фотографії
    }
    public String delete(Photo photo){
        photoRepository.delete(photo);
        return "photo: "+photo.toString()+" has deleted";
    }
    public String deleteById(Long id){
        photoRepository.deleteById(id);
        return "photo with id:"+id+" has deleted";
    }
    public String deleteAll(){
        photoRepository.deleteAll();
        return "all photos has deleted";
    }

}