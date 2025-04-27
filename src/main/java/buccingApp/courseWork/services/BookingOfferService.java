package buccingApp.courseWork.services;

import buccingApp.courseWork.dto.MyBookingOfferDTO;
import buccingApp.courseWork.dto.ReceivedBookingOfferDTO;
import buccingApp.courseWork.models.BookingOffer;
import buccingApp.courseWork.models.HouseForRent;
import buccingApp.courseWork.repositories.BookingOfferRepository;
import buccingApp.courseWork.repositories.HouseForRentRepository;
import buccingApp.courseWork.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingOfferService {

    private final BookingOfferRepository bookingOfferRepository;
    private final HouseForRentRepository houseForRentRepository;
    private final UserRepository userRepository;

    public BookingOfferService(BookingOfferRepository bookingOfferRepository, HouseForRentRepository houseForRentRepository, UserRepository userRepository) {
        this.bookingOfferRepository = bookingOfferRepository;
        this.houseForRentRepository = houseForRentRepository;
        this.userRepository = userRepository;
    }

    public List<BookingOffer> getAllBookingOffers() {
        return bookingOfferRepository.findAll();
    }

    public List<MyBookingOfferDTO> getBookingOffersByOwnerId(Long userId) {
        List<BookingOffer> offers = bookingOfferRepository.findByLessorId(userId);
        return offers.stream().map(offer -> {
            MyBookingOfferDTO dto = new MyBookingOfferDTO();
            dto.setId(offer.getId());
            dto.setHouseTitle(offer.getHouseOffer() != null ? offer.getHouseOffer().getTitle() : "Невідомо");
            dto.setOfferFrom(offer.getOfferFrom());
            dto.setOfferTo(offer.getOfferTo());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<ReceivedBookingOfferDTO> getBookingOffersForOwnerHouses(Long ownerId) {
        List<HouseForRent> ownerHouses = houseForRentRepository.findByOwner_Id(ownerId);
        List<Long> houseIds = ownerHouses.stream().map(HouseForRent::getId).collect(Collectors.toList());
        List<BookingOffer> offers = bookingOfferRepository.findByHouseOffer_IdIn(houseIds);
        return offers.stream().map(offer -> {
            ReceivedBookingOfferDTO dto = new ReceivedBookingOfferDTO();
            dto.setId(offer.getId());
            dto.setAuthorLogin(userRepository.findById(offer.getLessorId())
                    .map(user -> user.getLogin())
                    .orElse("Невідомо"));
            dto.setAuthorPhoneNumber(userRepository.findById(offer.getLessorId())
                    .map(user -> user.getPhoneNumber())
                    .orElse("Невідомо"));
            dto.setHouseTitle(offer.getHouseOffer() != null ? offer.getHouseOffer().getTitle() : "Невідомо");
            dto.setOfferFrom(offer.getOfferFrom());
            dto.setOfferTo(offer.getOfferTo());
            return dto;
        }).collect(Collectors.toList());
    }

    public BookingOffer saveBookingOffer(BookingOffer bookingOffer) {
        return bookingOfferRepository.save(bookingOffer);
    }

    public List<BookingOffer> saveBookingOffer(List<BookingOffer> bookingOffers) {
        return bookingOfferRepository.saveAll(bookingOffers);
    }

    public Optional<BookingOffer> getById(Long id) {
        return bookingOfferRepository.findById(id);
    }

    public HouseForRent saveBookingOffer(BookingOffer bookingOffer, Long id) {
        BookingOffer newBookingOffer = bookingOffer;
        HouseForRent houseForRent = houseForRentRepository.getById(id);
        houseForRent.addHouseOffer(newBookingOffer);
        bookingOfferRepository.save(newBookingOffer);
        return houseForRent;
    }

    public String delete(BookingOffer bookingOffer) {
        bookingOfferRepository.delete(bookingOffer);
        return "bookingOffer: " + bookingOffer.toString() + " has deleted";
    }

    public String deleteById(Long id) {
        bookingOfferRepository.deleteById(id);
        return "bookingOffer with id:" + id + " has deleted";
    }

    public String deleteAll() {
        bookingOfferRepository.deleteAll();
        return "all bookingOffers has deleted";
    }
}