package buccingApp.courseWork.services;

import buccingApp.courseWork.models.BookingOffer;
import buccingApp.courseWork.models.HouseForRent;
import buccingApp.courseWork.models.Review;
import buccingApp.courseWork.repositories.BookingOfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingOfferService {

    private final BookingOfferRepository bookingOfferRepository;

    public BookingOfferService(BookingOfferRepository bookingOfferRepository){
        this.bookingOfferRepository = bookingOfferRepository;
    }
    public List<BookingOffer> getAllBookingOffers(){
        return bookingOfferRepository.findAll();
    }
    public BookingOffer createBookingOffer(BookingOffer bookingOffer){
        return bookingOfferRepository.save(bookingOffer);
    }
    public List<BookingOffer> createBookingOffers(List<BookingOffer> bookingOffers){
        return bookingOfferRepository.saveAll(bookingOffers);
    }
    public Optional<BookingOffer> getById(Long id){
        return bookingOfferRepository.findById(id);
    }
}
