package by.schesnovich.CarRentApp.dto;

import by.schesnovich.CarRentApp.security.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@Builder
public class RegistrationDto {
    @NotBlank
    private String username;
    @Size(min = 8)
    private String password;
    private Role authority;
}
