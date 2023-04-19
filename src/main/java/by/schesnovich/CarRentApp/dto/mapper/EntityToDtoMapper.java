package by.schesnovich.CarRentApp.dto.mapper;

public interface EntityToDtoMapper <Dto, Entity> {
    Dto mapToDto(Entity entity);
}
