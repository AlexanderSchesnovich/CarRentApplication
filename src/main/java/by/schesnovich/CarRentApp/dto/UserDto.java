package by.schesnovich.CarRentApp.dto;

import by.schesnovich.CarRentApp.security.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Builder
public class UserDto {
    @NotBlank
    private String username;
    private Role authority;
}
