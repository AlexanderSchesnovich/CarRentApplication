package by.schesnovich.CarRentApp.controller;

import by.schesnovich.CarRentApp.dto.CarDto;
import by.schesnovich.CarRentApp.dto.mapper.DtoToEntityMapper;
import by.schesnovich.CarRentApp.entity.Car;
import by.schesnovich.CarRentApp.service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/car")
public class CarController {

    private final Service<Car, Long> carService;
    private final DtoToEntityMapper<CarDto, Car> carMapper;

    @PostMapping
    public void createCar(@Valid @RequestBody CarDto carDto) {
        carService.create(carMapper.mapToEntity(carDto));
    }

    @GetMapping
    public List<Car> getCar() {
        return carService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@NotNull @PathVariable Long id) {
        carService.delete(id);
    }

    @PutMapping("/{id}")
    public void updateCar(@NotNull @PathVariable Long id, @Valid @RequestBody CarDto carDto) {
        carService.update(id, carMapper.mapToEntity(carDto));
    }
}
