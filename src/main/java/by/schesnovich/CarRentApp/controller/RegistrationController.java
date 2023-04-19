package by.schesnovich.CarRentApp.controller;

import by.schesnovich.CarRentApp.dto.mapper.DtoToEntityMapper;
import by.schesnovich.CarRentApp.dto.RegistrationDto;
import by.schesnovich.CarRentApp.entity.User;
import by.schesnovich.CarRentApp.service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/registration")
public class RegistrationController {
    private final Service<User, Long> userService;
    private final DtoToEntityMapper<RegistrationDto, User> userRegistrationRequestToUserMapper;

    @PostMapping()
    public void registerUser(@Valid @RequestBody RegistrationDto registrationDto) {
        userService.create(userRegistrationRequestToUserMapper.mapToEntity(registrationDto));
    }
}
