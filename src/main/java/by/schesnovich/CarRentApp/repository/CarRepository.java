package by.schesnovich.CarRentApp.repository;

import by.schesnovich.CarRentApp.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

}
