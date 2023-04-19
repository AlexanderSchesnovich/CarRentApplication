package by.schesnovich.CarRentApp.dto.mapper;

import by.schesnovich.CarRentApp.dto.CarDto;
import by.schesnovich.CarRentApp.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarDtoToCarMapper implements DtoToEntityMapper<CarDto, Car> {
    @Override
    public Car mapToEntity(CarDto carDto) {
        return Car.builder()
                .manufacturer(carDto.getManufacturer())
                .model(carDto.getModel())
                .year(carDto.getYear())
                .mileage(carDto.getMileage())
                .costPerHour(carDto.getCostPerHour())
                .costPerMile(carDto.getCostPerMile())
                .isRented(carDto.isRented())
                .build();
    }
}
