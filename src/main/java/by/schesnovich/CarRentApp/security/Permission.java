package by.schesnovich.CarRentApp.security;

import lombok.Getter;

@Getter
public enum Permission {
    CAR_CREATE,
    CAR_GET,
    CAR_DELETE,
    CAR_UPDATE,
    USER_GET,
    USER_DELETE,
    USER_UPDATE,
    RENT_CREATE,
    RENT_FINISH,
    RENT_GET,
    RENT_DELETE,
    RENT_UPDATE
}
