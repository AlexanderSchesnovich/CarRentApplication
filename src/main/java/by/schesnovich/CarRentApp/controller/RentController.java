package by.schesnovich.CarRentApp.controller;

import by.schesnovich.CarRentApp.dto.RentDto;
import by.schesnovich.CarRentApp.dto.mapper.EntityAndDtoMapper;
import by.schesnovich.CarRentApp.entity.Rent;
import by.schesnovich.CarRentApp.service.RentService;
import by.schesnovich.CarRentApp.service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/rent")
public class RentController {

    private final Service<Rent, Long> rentService;
    private final EntityAndDtoMapper<RentDto, Rent> rentMapper;

    @PostMapping
    public void createRent(@Valid @RequestBody RentDto rentDto) {
        rentService.create(rentMapper.mapToEntity(rentDto));
    }

    @GetMapping
    public List<RentDto> getRents() {
        return rentMapper.mapCollectionToDtoList(rentService.getAll());
    }

    @DeleteMapping("/{id}")
    public void deleteRent(@NotNull @PathVariable Long id) {
        rentService.delete(id);
    }

    @PutMapping("/{id}")
    public void updateRent(@NotNull @PathVariable Long id, @Valid @RequestBody RentDto rentDto) {
        rentService.update(id, rentMapper.mapToEntity(rentDto));
    }

    @PutMapping("/{id}/{mileage}")
    public void stopRent(@NotNull @PathVariable Long id, @NotNull @PathVariable int mileage) {
        ((RentService)rentService).stopRent(id, mileage);
    }
}
