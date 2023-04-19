package by.schesnovich.CarRentApp.dto.mapper;

import by.schesnovich.CarRentApp.dto.RentDto;
import by.schesnovich.CarRentApp.entity.Car;
import by.schesnovich.CarRentApp.entity.Rent;
import by.schesnovich.CarRentApp.entity.User;
import by.schesnovich.CarRentApp.error.exception.ResourceNotFoundException;
import by.schesnovich.CarRentApp.repository.CarRepository;
import by.schesnovich.CarRentApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static by.schesnovich.CarRentApp.error.message.ExceptionMessage.CAR_NOT_FOUND_MSG;

@Component
@RequiredArgsConstructor
public class RentDtoToRentMapper implements EntityAndDtoMapper<RentDto, Rent> {
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    @Override
    public Rent mapToEntity(RentDto rentDto) {
        return Rent.builder()
                .car(getRentingCar(rentDto.getCarId()))
                .user(getAuthenticatedUser())
                .mileage(rentDto.getMileage())
                .rentStartDate(rentDto.getRentStartDate())
                .rentEndDate(rentDto.getRentEndDate())
                .cost(rentDto.getCost())
                .build();
    }

    private User getAuthenticatedUser() {
        String authenticatedUsername = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return userRepository.findByUsername(authenticatedUsername).get();
    }

    private Car getRentingCar(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(CAR_NOT_FOUND_MSG, id)));
    }

    public RentDto mapToDto(Rent rent) {
        return RentDto.builder()
                .carId(rent.getCar().getId())
                .userId(rent.getUser().getId())
                .mileage(rent.getMileage())
                .rentStartDate(rent.getRentStartDate())
                .rentEndDate(rent.getRentEndDate())
                .cost(rent.getCost())
                .build();
    }

    public List<RentDto> mapCollectionToDtoList(Collection<Rent> rents) {
        return rents.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
