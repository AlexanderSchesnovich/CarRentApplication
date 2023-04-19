package by.schesnovich.CarRentApp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class RentDto {
    @NotNull
    private Long carId;
    private Long userId;
    @PositiveOrZero
    private int mileage;
    private LocalDateTime rentStartDate;
    private LocalDateTime rentEndDate;
    @Positive
    private BigDecimal cost;
}
