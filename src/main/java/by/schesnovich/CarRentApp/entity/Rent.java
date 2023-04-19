package by.schesnovich.CarRentApp.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"car", "client"})
public class Rent {
    @Id
    @Column(name = "rent_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
    @PositiveOrZero
    private int mileage;
    @Column(name = "rent_start_date")
    private LocalDateTime rentStartDate;
    @Column(name = "rent_end_date")
    private LocalDateTime rentEndDate;
    @Positive
    private BigDecimal cost;
}
