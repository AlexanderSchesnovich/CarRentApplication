package by.schesnovich.CarRentApp.dto.mapper;

import java.util.Collection;
import java.util.List;

public interface EntityAndDtoMapper<Dto, Entity> extends DtoToEntityMapper<Dto, Entity>, EntityToDtoMapper<Dto, Entity> {
    List<Dto> mapCollectionToDtoList(Collection<Entity> entityCollection);
}
