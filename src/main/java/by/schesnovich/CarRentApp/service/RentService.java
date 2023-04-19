package by.schesnovich.CarRentApp.service;

import by.schesnovich.CarRentApp.entity.Car;
import by.schesnovich.CarRentApp.entity.Rent;
import by.schesnovich.CarRentApp.error.exception.CarAlreadyRentedException;
import by.schesnovich.CarRentApp.error.message.ExceptionMessage;
import by.schesnovich.CarRentApp.repository.RentRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@org.springframework.stereotype.Service
public class RentService extends Service<Rent, Long> {

    private final RentRepository repository;

    public RentService(RentRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void create(Rent rent) {
        Rent startedRent = startRent(rent);
        repository.save(startedRent);
    }

    private Rent startRent(Rent rent) {
        Car car = rent.getCar();
        if (car.isRented()) {
            throw new CarAlreadyRentedException(String.format(ExceptionMessage.CAR_ALREADY_RENTED_MSG, car.getId()));
        }
        car.setRented(true);
        rent.setRentStartDate(LocalDateTime.now());
        return rent;
    }

    public void stopRent(Long id, int mileage) {
        Rent rent = findById(id);
        rent.setRentEndDate(LocalDateTime.now());
        rent.setMileage(mileage);
        Car car = rent.getCar();
        car.setMileage(calculateCarMileage(rent));
        car.setRented(false);
        rent.setCost(calculatePrice(rent));
        repository.save(rent);
    }

    private BigDecimal calculatePrice(Rent rent) {
        BigDecimal hours = BigDecimal.valueOf(ChronoUnit.HOURS.between(rent.getRentStartDate(), rent.getRentEndDate()));
        return (rent.getCar().getCostPerHour().multiply(hours))
                .add(rent.getCar().getCostPerMile().multiply(BigDecimal.valueOf(rent.getMileage())));
    }

    private int calculateCarMileage(Rent rent) {
        Car car = rent.getCar();
        return car.getMileage() + rent.getMileage();
    }

    @Override
    public void update(Long id, Rent rent) {
        checkIfExists(id);
        rent.setId(id);
        repository.save(rent);
    }
}
