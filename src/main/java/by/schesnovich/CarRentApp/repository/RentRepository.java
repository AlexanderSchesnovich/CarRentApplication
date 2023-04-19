package by.schesnovich.CarRentApp.repository;

import by.schesnovich.CarRentApp.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Long> {
}
