package buccingApp.courseWork.services;

import buccingApp.courseWork.dto.HouseFilterDTO;
import buccingApp.courseWork.models.HouseForRent;
import buccingApp.courseWork.repositories.HouseForRentRepository;
import buccingApp.courseWork.specifications.HouseSpecifications;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseForRentService {
    private final HouseForRentRepository houseForRentRepository;

    public HouseForRentService(HouseForRentRepository houseForRentRepository){
        this.houseForRentRepository=houseForRentRepository;
    }
    public List<HouseForRent> getAllHousesForRent(){
        return houseForRentRepository.findAll();
    }
    public List<HouseForRent> getHousesByUserId(Long userId){
        return houseForRentRepository.findByOwner_Id(userId);
    }
    public HouseForRent createHouseForRent(HouseForRent houseForRent){
        return houseForRentRepository.save(houseForRent);
    }
    public List<HouseForRent> createHousesForRent(List<HouseForRent> housesForRent){
        return houseForRentRepository.saveAll(housesForRent);
    }
    public Optional<HouseForRent> getById(Long id){
        return houseForRentRepository.findById(id);
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

}
