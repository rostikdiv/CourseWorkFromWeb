package buccingApp.courseWork.repositories;

import buccingApp.courseWork.models.RentedHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RentedHouseRepository extends JpaRepository<RentedHouse,Long> {
}
