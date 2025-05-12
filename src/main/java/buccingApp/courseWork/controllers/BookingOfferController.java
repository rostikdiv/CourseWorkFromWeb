package buccingApp.courseWork.controllers;

import buccingApp.courseWork.dto.MyBookingOfferDTO;
import buccingApp.courseWork.dto.ReceivedBookingOfferDTO;
import buccingApp.courseWork.models.BookingOffer;
import buccingApp.courseWork.models.HouseForRent;
import buccingApp.courseWork.models.User;
import buccingApp.courseWork.repositories.UserRepository;
import buccingApp.courseWork.services.BookingOfferService;
import buccingApp.courseWork.services.HouseForRentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookOffer")
public class BookingOfferController {

    private final BookingOfferService bookingOfferService;

    private final UserRepository userRepository;

    public BookingOfferController(BookingOfferService bookingOfferService, UserRepository userRepository){
        this.bookingOfferService = bookingOfferService;
        this.userRepository =userRepository;
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
    @PostMapping("/toHouse/{id}")
    public HouseForRent updateHouseForReent (@RequestBody BookingOffer bookingOffer, @PathVariable Long id){
        return bookingOfferService.saveBookingOffer(bookingOffer,id);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getBookingOfferById(@PathVariable Long id){
        return bookingOfferService.getById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok) // Якщо користувач знайдений
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
    }
    @GetMapping("/getAlBbyUserId/{userId}")
    public List<MyBookingOfferDTO> getBookingOffersByUserId(@PathVariable Long userId) {
        return bookingOfferService.getBookingOffersByOwnerId(userId);
    }

    @GetMapping("/owner/{ownerId}")
    public List<ReceivedBookingOfferDTO> getBookingOffersForOwnerHouses(@PathVariable Long ownerId) {
        return bookingOfferService.getBookingOffersForOwnerHouses(ownerId);
    }

//    // Оновлений ендпоінт для отримання пропозицій поточного користувача
//    @GetMapping("/myOffers")
//    public ResponseEntity<List<BookingOffer>> getMyBookingOffers() {
//        // Отримуємо поточного користувача через Spring Security
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName(); // Отримуємо login користувача
//        User user = userRepository.findByLogin(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Отримуємо пропозиції для цього користувача
//        List<BookingOffer> myOffers = bookingOfferService.getBookingOffersByOwnerId(user.getId());
//        return ResponseEntity.ok(myOffers);
//    }

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
