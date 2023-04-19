package by.schesnovich.CarRentApp.dto.mapper;

import by.schesnovich.CarRentApp.dto.UserDto;
import by.schesnovich.CarRentApp.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoToUserMapper implements EntityAndDtoMapper<UserDto, User> {
    @Override
    public User mapToEntity(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .authority(userDto.getAuthority())
                .build();
    }

    public UserDto mapToDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .authority(user.getAuthority())
                .build();
    }

    public List<UserDto> mapCollectionToDtoList(Collection<User> users) {
        return users.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
