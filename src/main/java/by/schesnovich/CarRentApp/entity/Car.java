package by.schesnovich.CarRentApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "rents")
public class Car {
    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String manufacturer;
    private String model;
    private int year;
    private int mileage;
    @Column(name = "cost_per_mile")
    private BigDecimal costPerMile;
    @Column(name = "cost_per_hour")
    private BigDecimal costPerHour;
    @Column(name = "is_rented")
    private boolean isRented;
    @JsonIgnore
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Rent> rents = new HashSet<>();
}
