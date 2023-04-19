package by.schesnovich.CarRentApp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class CarDto {
    @NotBlank
    private String manufacturer;
    @NotBlank
    private String model;
    @Positive
    private int year;
    @PositiveOrZero
    private int mileage;
    @Positive
    private BigDecimal costPerMile;
    @Positive
    private BigDecimal costPerHour;
    private boolean isRented;
}
