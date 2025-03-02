package buccingApp.courseWork.repositories;

import buccingApp.courseWork.models.BookingOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BookingOfferRepository extends JpaRepository<BookingOffer, Long> {
}
