package buccingApp.courseWork.models;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "offers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long LessorId;

    @Column(nullable = false)
    private Long CustomerId;

    @Column(nullable = false)
    private LocalDate OfferFrom;

    @Column(nullable = false)
    private LocalDate OfferTo;
}
