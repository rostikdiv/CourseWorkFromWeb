package buccingApp.courseWork.models;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "rentedHouses")  // Зазвичай таблиця "users", бо "user" зарезервоване слово в SQL
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentedHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long LessorId;

    @Column(nullable = false)
    private Long CustomerId;

    @Column(nullable = false)
    private LocalDate rentedFrom;

    @Column(nullable = false)
    private LocalDate rentedTo;


}
