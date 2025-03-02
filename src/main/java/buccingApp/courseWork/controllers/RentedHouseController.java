package buccingApp.courseWork.controllers;

import buccingApp.courseWork.models.RentedHouse;
import buccingApp.courseWork.models.User;
import buccingApp.courseWork.services.RentedHouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rented")
public class RentedHouseController {
    private final RentedHouseService rentedHouseService;

    public RentedHouseController(RentedHouseService rentedHouseService){
        this.rentedHouseService = rentedHouseService;
    }

    @RequestMapping
    public List<RentedHouse> getAllRentedHouse(){
        return rentedHouseService.getAllRentedHouse();
    }

    @PostMapping
    public RentedHouse createRentedHouse(@RequestBody RentedHouse rentedHouse){
        return rentedHouseService.createRentedHouse(rentedHouse);
    }
    @GetMapping("/getById/{id}")
    public Optional<RentedHouse> getRentedHouseById(@PathVariable Long id){
        return rentedHouseService.getById(id);
    }

}
