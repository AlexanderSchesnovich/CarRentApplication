package by.schesnovich.CarRentApp.controller;

import by.schesnovich.CarRentApp.dto.UserDto;
import by.schesnovich.CarRentApp.dto.mapper.EntityAndDtoMapper;
import by.schesnovich.CarRentApp.entity.User;
import by.schesnovich.CarRentApp.service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final Service<User, Long> userService;
    private final EntityAndDtoMapper<UserDto, User> userMapper;

    @GetMapping
    public List<UserDto> getUsers() {
        return userMapper.mapCollectionToDtoList(userService.getAll());
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@NotNull @PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@NotNull @PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        userService.update(id, userMapper.mapToEntity(userDto));
    }
}
