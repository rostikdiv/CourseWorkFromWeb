package buccingApp.courseWork.repositories;

import buccingApp.courseWork.models.BookingOffer;
import buccingApp.courseWork.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BookingOfferRepository extends JpaRepository<BookingOffer, Long> {
    List<BookingOffer> findByLessorId(Long lessorId);
    List<BookingOffer> findByHouseOffer_IdIn(List<Long> houseIds);
}
