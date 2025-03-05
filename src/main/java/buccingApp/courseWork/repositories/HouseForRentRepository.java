package buccingApp.courseWork.repositories;

import buccingApp.courseWork.models.HouseForRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface HouseForRentRepository extends JpaRepository<HouseForRent,Long>,
        JpaSpecificationExecutor<HouseForRent> {
    public List<HouseForRent> findByOwner_Id(Long ownerId);
}
