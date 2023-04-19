package by.schesnovich.CarRentApp.service;

import by.schesnovich.CarRentApp.entity.Car;
import by.schesnovich.CarRentApp.entity.Rent;
import by.schesnovich.CarRentApp.error.message.ExceptionMessage;
import by.schesnovich.CarRentApp.repository.RentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentServiceTest {
    @Spy
    private RentRepository repository;
    @Spy
    @InjectMocks
    private RentService rentService;

    @BeforeEach
    void setUp() {
        rentService = new RentService(repository);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void createRentTest() {
        Car car = Mockito.spy(Car.builder().build());
        Rent rent = Mockito.spy(Rent.builder().car(car).build());
        rentService.create(rent);
        Mockito.verify(repository).save(rent);
    }

    @Test
    void startRentWhenCarNotRentedTest() {
        Car car = Mockito.spy(Car.builder().build());
        Rent rent = Mockito.spy(Rent.builder().car(car).build());
        try {
            Method method = RentService.class.getDeclaredMethod("startRent", Rent.class);
            method.setAccessible(true);
            method.invoke(rentService, rent);
            assertThat(rent.getCar().isRented()).isTrue();
            assertThat(rent.getRentStartDate()).isEqualToIgnoringNanos(LocalDateTime.now());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void startRentWhenCarAlreadyRentedTest() {
        Car car = Car.builder()
                .id(1L)
                .isRented(true)
                .build();
        Rent rent = Rent.builder()
                .car(car)
                .build();
        try {
            Method method = RentService.class.getDeclaredMethod("startRent", Rent.class);
            method.setAccessible(true);
            assertThatThrownBy(() -> method.invoke(rentService, rent))
                    .getCause()
                    .hasMessageContaining(String.format(ExceptionMessage.CAR_ALREADY_RENTED_MSG, car.getId()));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void calculatePriceTest() {
        BigDecimal expectedPrice = BigDecimal.valueOf(120);
        LocalDateTime startRentTime = LocalDateTime.of(2023, Month.APRIL, 6, 12, 0);
        LocalDateTime endRentTime = startRentTime.plusHours(2L);
        Car car = Car.builder()
                .costPerHour(BigDecimal.valueOf(10))
                .costPerMile(BigDecimal.valueOf(1))
                .build();
        Rent rent = Rent.builder()
                .mileage(100)
                .car(car)
                .rentStartDate(startRentTime)
                .rentEndDate(endRentTime)
                .build();
        try {
            Method method = RentService.class.getDeclaredMethod("calculatePrice", Rent.class);
            method.setAccessible(true);
            BigDecimal actualPrice = (BigDecimal) method.invoke(rentService, rent);
            assertThat(expectedPrice).isEqualTo(actualPrice);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void stopRentIfRentExistsTest() {
        Car car = Mockito.spy(Car.builder().isRented(true).costPerMile(BigDecimal.valueOf(10)).costPerHour(BigDecimal.valueOf(10)).mileage(1296).build());
        LocalDateTime startRent = LocalDateTime.of(2023, Month.APRIL, 9, 22, 25);
        Rent rent = Mockito.spy(Rent.builder().id(1L).car(car).rentStartDate(startRent).mileage(120).build());
        int mileage = 200;
        repository.save(rent);
        when(repository.findById(any())).thenReturn(Optional.of(rent));
        rentService.stopRent(1L, 200);
        try {
            Method method = RentService.class.getDeclaredMethod("calculateCarMileage", Rent.class);
            method.setAccessible(true);
            verify(rent).setRentEndDate(any());
            verify(rent).setMileage(mileage);
            verify(car).setMileage((int) method.invoke(rentService, rent));
            verify(car).setRented(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}