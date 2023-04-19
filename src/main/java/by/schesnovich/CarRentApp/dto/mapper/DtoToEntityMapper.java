package by.schesnovich.CarRentApp.dto.mapper;

public interface DtoToEntityMapper<Dto, Entity>{
    Entity mapToEntity(Dto dto);
}
