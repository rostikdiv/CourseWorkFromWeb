package buccingApp.courseWork.controllers;

import buccingApp.courseWork.models.BookingOffer;
import buccingApp.courseWork.models.HouseForRent;
import buccingApp.courseWork.models.User;
import buccingApp.courseWork.services.BookingOfferService;
import buccingApp.courseWork.services.HouseForRentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookOffer")
public class BookingOfferController {

    private final BookingOfferService bookingOfferService;

    public BookingOfferController(BookingOfferService bookingOfferService){
        this.bookingOfferService = bookingOfferService;
    }

    @RequestMapping
    public List<BookingOffer> getAllBookingOffers(){
        return bookingOfferService.getAllBookingOffers();
    }

    @PostMapping
    public BookingOffer createBookingOffer(@RequestBody BookingOffer bookingOffer){
        return bookingOfferService.createBookingOffer(bookingOffer);
    }
    @GetMapping("/getById/{id}")
    public Optional<BookingOffer> getBookingOfferById(@PathVariable Long id){
        return bookingOfferService.getById(id);
    }
}
