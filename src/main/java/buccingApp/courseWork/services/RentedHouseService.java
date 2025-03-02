package buccingApp.courseWork.services;

import buccingApp.courseWork.models.RentedHouse;
import buccingApp.courseWork.models.Review;
import buccingApp.courseWork.models.User;
import buccingApp.courseWork.repositories.RentedHouseRepository;
import buccingApp.courseWork.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentedHouseService {
    private final RentedHouseRepository rentedHouseRepository;

    public RentedHouseService(RentedHouseRepository rentedHouseRepository) {
        this.rentedHouseRepository = rentedHouseRepository;
    }
    public List<RentedHouse> getAllRentedHouse(){
        return rentedHouseRepository.findAll();
    }
    public RentedHouse createRentedHouse(RentedHouse rentedHouse){
        return rentedHouseRepository.save(rentedHouse);
    }
    public Optional<RentedHouse> getById(Long id){
        return rentedHouseRepository.findById(id);
    }
}
