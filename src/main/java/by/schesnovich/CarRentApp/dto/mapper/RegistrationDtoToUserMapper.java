package by.schesnovich.CarRentApp.dto.mapper;

import by.schesnovich.CarRentApp.dto.RegistrationDto;
import by.schesnovich.CarRentApp.entity.User;
import org.springframework.stereotype.Component;

@Component
public class RegistrationDtoToUserMapper implements DtoToEntityMapper<RegistrationDto, User> {

    @Override
    public User mapToEntity(RegistrationDto request) {
        return User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .authority(request.getAuthority())
                .isAccountNonLocked(true)
                .isEnabled(true)
                .isAccountNonExpired(true)
                .isCredentialsNonExpired(true)
                .build();
    }
}
