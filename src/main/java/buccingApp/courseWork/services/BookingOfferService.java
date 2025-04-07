package buccingApp.courseWork.services;

import buccingApp.courseWork.controllers.BookingOfferController;
import buccingApp.courseWork.models.BookingOffer;
import buccingApp.courseWork.models.HouseForRent;
import buccingApp.courseWork.models.Review;
import buccingApp.courseWork.repositories.BookingOfferRepository;
import buccingApp.courseWork.repositories.HouseForRentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingOfferService {

    private final BookingOfferRepository bookingOfferRepository;
    private final HouseForRentRepository houseForRentRepository;

    public BookingOfferService(BookingOfferRepository bookingOfferRepository,HouseForRentRepository houseForRentRepository){
        this.bookingOfferRepository = bookingOfferRepository;
        this.houseForRentRepository = houseForRentRepository;
    }
    public List<BookingOffer> getAllBookingOffers(){
        return bookingOfferRepository.findAll();
    }
    public List<BookingOffer> getBookingOffersByOwnerId(Long userId) {
        return bookingOfferRepository.findByLessorId(userId);
    }
    public BookingOffer saveBookingOffer(BookingOffer bookingOffer){
        return bookingOfferRepository.save(bookingOffer);
    }
    public List<BookingOffer> saveBookingOffer(List<BookingOffer> bookingOffers){
        return bookingOfferRepository.saveAll(bookingOffers);
    }
    public Optional<BookingOffer> getById(Long id){
        return bookingOfferRepository.findById(id);
    }

    public HouseForRent saveBookingOffer(BookingOffer bookingOffer, Long id){
        BookingOffer newBookingOffer = bookingOffer;
        HouseForRent houseForRent = houseForRentRepository.getById(id);
        houseForRent.addHouseOffer(newBookingOffer);
        bookingOfferRepository.save(newBookingOffer);
        return houseForRent;
    }

    public String delete(BookingOffer bookingOffer){
        bookingOfferRepository.delete(bookingOffer);
        return "bookingOffer: "+bookingOffer.toString()+" has deleted";
    }
    public String deleteById(Long id){
        bookingOfferRepository.deleteById(id);
        return "bookingOffer with id:"+id+" has deleted";
    }
    public String deleteAll(){
        bookingOfferRepository.deleteAll();
        return "all bookingOffers has deleted";
    }
}
