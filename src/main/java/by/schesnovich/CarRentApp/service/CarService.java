package by.schesnovich.CarRentApp.service;

import by.schesnovich.CarRentApp.entity.Car;
import by.schesnovich.CarRentApp.repository.CarRepository;

@org.springframework.stereotype.Service
public class CarService extends Service<Car, Long> {

    private final CarRepository repository;

    public CarService(CarRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void update(Long id, Car car) {
        checkIfExists(id);
        car.setId(id);
        repository.save(car);
    }
}
