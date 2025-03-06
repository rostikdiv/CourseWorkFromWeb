package buccingApp.courseWork.controllers;

import buccingApp.courseWork.models.BookingOffer;
import buccingApp.courseWork.models.HouseForRent;
import buccingApp.courseWork.models.User;
import buccingApp.courseWork.services.BookingOfferService;
import buccingApp.courseWork.services.HouseForRentService;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public List<BookingOffer> getAllBookingOffers(){
        return bookingOfferService.getAllBookingOffers();
    }

    @PostMapping
    public BookingOffer createBookingOffer(@RequestBody BookingOffer bookingOffer){
        return bookingOfferService.saveBookingOffer(bookingOffer);
    }
    @PostMapping("/batch")
    public List<BookingOffer> createBookingOffers(@RequestBody List<BookingOffer> bookingOffers){
        return bookingOfferService.saveBookingOffer(bookingOffers);
    }

    @GetMapping("/getById/{id}")
    public Optional<BookingOffer> getBookingOfferById(@PathVariable Long id){
        return bookingOfferService.getById(id);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<BookingOffer> updateUser(@PathVariable Long id, @RequestBody BookingOffer updatedBookingOffer) {
        // Знаходимо користувача в базі
        BookingOffer bookingOffer = bookingOfferService.getById(id)
                .orElseThrow(() -> new RuntimeException("BookingOffer not found"));

        // Оновлюємо поля, якщо передані нові значення
        if(updatedBookingOffer.getOfferTo() != null){bookingOffer.setOfferTo(updatedBookingOffer.getOfferTo());}
        if (updatedBookingOffer.getOfferFrom() != null){bookingOffer.setOfferFrom(updatedBookingOffer.getOfferFrom());}

        BookingOffer savedBookingOffer = bookingOfferService.saveBookingOffer(bookingOffer);

        return ResponseEntity.ok(savedBookingOffer);
    }

    @PostMapping("/delete/bookingOffer")
    public String delete(@RequestBody BookingOffer bookingOffer){
        return bookingOfferService.delete(bookingOffer);
    }
    @GetMapping("delete/byId/{id}")
    public String deleteById(@PathVariable Long id){
        return bookingOfferService.deleteById(id);
    }
    @GetMapping("/delete/all")
    public String deleteAll(){
        return bookingOfferService.deleteAll();
    }


}
