package buccingApp.courseWork.controllers;

import buccingApp.courseWork.dto.HouseFilterDTO;
import buccingApp.courseWork.models.HouseForRent;
import buccingApp.courseWork.models.RentedHouse;
import buccingApp.courseWork.models.User;
import buccingApp.courseWork.services.HouseForRentService;
import buccingApp.courseWork.services.RentedHouseService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ForRent")
public class HouseForRentController {

    private final HouseForRentService houseForRentService;

    public HouseForRentController(HouseForRentService houseForRentService){
        this.houseForRentService = houseForRentService;
    }

    @RequestMapping
    public List<HouseForRent> getAllHousesForRent(){
        return houseForRentService.getAllHousesForRent();
    }

    @PostMapping
    public HouseForRent createHouseForRent(@RequestBody HouseForRent houseForRent){
        return houseForRentService.createHouseForRent(houseForRent);
    }
    @PostMapping("/batch")
    public List<HouseForRent> createMultipleHouses(@RequestBody List<HouseForRent> housesForRent) {

        return houseForRentService.createHousesForRent(housesForRent);

    }
    @GetMapping("/getById/{id}")
    public Optional<HouseForRent> getHouseForRentById(@PathVariable Long id){
        return houseForRentService.getById(id);
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

}
