package buccingApp.courseWork.services;

import buccingApp.courseWork.dto.HouseFilterDTO;
import buccingApp.courseWork.models.HouseForRent;
import buccingApp.courseWork.models.Review;
import buccingApp.courseWork.models.User;
import buccingApp.courseWork.repositories.HouseForRentRepository;
import buccingApp.courseWork.repositories.UserRepository;
import buccingApp.courseWork.specifications.HouseSpecifications;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class HouseForRentService {
    private final HouseForRentRepository houseForRentRepository;
    private final UserRepository userRepository;

    public HouseForRentService(HouseForRentRepository houseForRentRepository, UserRepository userRepository){
        this.houseForRentRepository=houseForRentRepository;
        this.userRepository =userRepository;
    }
    public List<HouseForRent> getAllHousesForRent(){
        return houseForRentRepository.findAll();
    }
    public List<HouseForRent> getHousesByUserId(Long userId){
        return houseForRentRepository.findByOwner_Id(userId);
    }
    public HouseForRent saveHouseForRent(HouseForRent houseForRent){
        return houseForRentRepository.save(houseForRent);
    }
    public List<HouseForRent> saveHouseForRent(List<HouseForRent> housesForRent){
        return houseForRentRepository.saveAll(housesForRent);
    }
    public Optional<HouseForRent> getById(Long id){
        return houseForRentRepository.findById(id);
    }
    public HouseForRent saveUser(HouseForRent houseForRent,Long id){
        HouseForRent newHouseForRent= houseForRent;
        User user = userRepository.getById(id);
        user.addHouseForRent(newHouseForRent);
        houseForRentRepository.save(newHouseForRent);
        return newHouseForRent;
    }


    public List<HouseForRent> findWithFilter(HouseFilterDTO filter) {
        Specification<HouseForRent> spec = Specification
                .where(HouseSpecifications.hasCity(filter.getCity()))
                .and(HouseSpecifications.priceGreaterThanOrEqual(filter.getMinPrice()))
                .and(HouseSpecifications.priceLessThanOrEqual(filter.getMaxPrice()))
                .and(HouseSpecifications.roomsGreaterThanOrEqual(filter.getMinRooms()))
                .and(HouseSpecifications.areaGreaterThanOrEqual(filter.getMinArea()))
                .and(HouseSpecifications.hasWifi(filter.getHasWifi()))
                .and(HouseSpecifications.hasParking(filter.getHasParking()))
                .and(HouseSpecifications.hasPool(filter.getHasPool()));

        // Пошук за ключовим словом у заголовку або описі
        if (filter.getKeyword() != null && !filter.getKeyword().isEmpty()) {
            spec = spec.and(
                    Specification.where(HouseSpecifications.titleContains(filter.getKeyword()))
                            .or(HouseSpecifications.descriptionContains(filter.getKeyword()))
            );
        }

        return houseForRentRepository.findAll(spec);
    }

    public HouseForRent update(Long id, HouseForRent updatedHouse) {
        Optional<HouseForRent> optionalHouse = getById(id);
        HouseForRent house = optionalHouse.get();
        house.setTitle(updatedHouse.getTitle());
        house.setDescription(updatedHouse.getDescription());
        house.setCity(updatedHouse.getCity());
        house.setRooms(updatedHouse.getRooms());
        house.setArea(updatedHouse.getArea());
        house.setPrice(updatedHouse.getPrice());
        // Оновлення списку фотографій
        house.setPhotos(updatedHouse.getPhotos());
        return houseForRentRepository.save(house);
    }

    public String delete(HouseForRent houseForRent){
        houseForRentRepository.delete(houseForRent);
        return "review: "+houseForRent.toString()+" has deleted";
    }
    public String deleteById(Long id){
        houseForRentRepository.deleteById(id);
        return "houseForRent with id:"+id+" has deleted";
    }
    public String deleteAll(){
        houseForRentRepository.deleteAll();
        return "all houseForRents has deleted";
    }

}
