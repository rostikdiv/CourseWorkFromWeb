package buccingApp.courseWork.controllers;

import buccingApp.courseWork.dto.HouseFilterDTO;
import buccingApp.courseWork.models.HouseForRent;
import buccingApp.courseWork.models.User;
import buccingApp.courseWork.services.HouseForRentService;
import buccingApp.courseWork.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/forRent")
@CrossOrigin(origins = "http://localhost:5000", allowCredentials = "true")
public class HouseForRentController {

    private final HouseForRentService houseForRentService;
    private final UserService userService;

    public HouseForRentController(HouseForRentService houseForRentService, UserService userService){
        this.houseForRentService = houseForRentService;
        this.userService = userService;
    }

    @GetMapping
    public List<HouseForRent> getAllHousesForRent(){
        return houseForRentService.getAllHousesForRent();
    }

    @PostMapping
    public HouseForRent createHouseForRent(@RequestBody HouseForRent houseForRent){
        return houseForRentService.saveHouseForRent(houseForRent);
    }
    @PostMapping("/toUser/{id}")
    public HouseForRent createHouseForRent(@RequestBody HouseForRent houseForRent, @PathVariable Long id){

        return houseForRentService.saveUser(houseForRent, id);
    }
    @PostMapping("/batch")
    public List<HouseForRent> createMultipleHouses(@RequestBody List<HouseForRent> housesForRent) {

        return houseForRentService.saveHouseForRent(housesForRent);

    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getHouseForRentById(@PathVariable Long id){
        return houseForRentService.getById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok) // Якщо користувач знайдений
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("House not found"));
    }
    @GetMapping("getById/all/{userId}")
    public List<HouseForRent> getHousesByUserId(@PathVariable Long userId){
        return houseForRentService.getHousesByUserId(userId);
    }

    // Ендпоінт для фільтрації через query параметри
    @GetMapping("/filter")
    public List<HouseForRent> filterHouses(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer minRooms,
            @RequestParam(required = false) Double minArea,
            @RequestParam(required = false) Boolean hasWifi,
            @RequestParam(required = false) Boolean hasParking,
            @RequestParam(required = false) Boolean hasPool,
            @RequestParam(required = false) String keyword) {

        HouseFilterDTO filter = new HouseFilterDTO();
        filter.setCity(city);
        filter.setMinPrice(minPrice);
        filter.setMaxPrice(maxPrice);
        filter.setMinRooms(minRooms);
        filter.setMinArea(minArea);
        filter.setHasWifi(hasWifi);
        filter.setHasParking(hasParking);
        filter.setHasPool(hasPool);
        filter.setKeyword(keyword);

        return houseForRentService.findWithFilter(filter);
    }

    // Ендпоінт для фільтрації через об'єкт у тілі запиту
    @PostMapping("/search")
    public List<HouseForRent> searchHouses(@RequestBody HouseFilterDTO filter) {
        return houseForRentService.findWithFilter(filter);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<HouseForRent> updateUser(@PathVariable Long id, @RequestBody HouseForRent updatedHouseForRent) {
        // Знаходимо користувача в базі
        HouseForRent houseForRent = houseForRentService.getById(id)
                .orElseThrow(() -> new RuntimeException("HouseForRent not found"));

        // Оновлюємо поля, якщо передані нові значення
        if (updatedHouseForRent.getTitle() != null) {
            houseForRent.setTitle(updatedHouseForRent.getTitle());
        }
        if (updatedHouseForRent.getDescription() != null) {
            houseForRent.setDescription(updatedHouseForRent.getDescription());
        }
        if (updatedHouseForRent.getCity() != null) {
            houseForRent.setCity(updatedHouseForRent.getCity());
        }
        if (updatedHouseForRent.getRooms() != 0) {
            houseForRent.setRooms(updatedHouseForRent.getRooms());
        }
        if (updatedHouseForRent.getArea() != 0) {
            houseForRent.setArea(updatedHouseForRent.getArea());
        }
        if (updatedHouseForRent.getPrice() != 0) {
            houseForRent.setPrice(updatedHouseForRent.getPrice());
        }
        if (updatedHouseForRent.getHasWifi() != null) {
            houseForRent.setHasWifi(updatedHouseForRent.getHasWifi());
        }
        if (updatedHouseForRent.getHasParking() != null) {
            houseForRent.setHasParking(updatedHouseForRent.getHasParking());
        }
        if (updatedHouseForRent.getHasPool() != null) {
            houseForRent.setHasPool(updatedHouseForRent.getHasPool());
        }

        // Оновлюємо список фотографій
        if (updatedHouseForRent.getPhotos() != null) {
            houseForRent.setPhotos(updatedHouseForRent.getPhotos());
        }

        HouseForRent savedHouseForRent = houseForRentService.saveHouseForRent(houseForRent);
        return ResponseEntity.ok(savedHouseForRent);
    }

    @PostMapping("/delete/houseForRent")
    public String delete(@RequestBody HouseForRent houseForRent){
        return houseForRentService.delete(houseForRent);
    }
    @GetMapping("delete/byId/{id}")
    public String deleteById(@PathVariable Long id){
        return houseForRentService.deleteById(id);
    }
    @GetMapping("/delete/all")
    public String deleteAll(){
        return houseForRentService.deleteAll();
    }

}
