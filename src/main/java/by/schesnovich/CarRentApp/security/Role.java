package by.schesnovich.CarRentApp.security;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static by.schesnovich.CarRentApp.security.Permission.*;

@RequiredArgsConstructor
@Getter
public enum Role {
    USER(Sets.newHashSet(CAR_GET,
            RENT_CREATE,
            RENT_FINISH)),
    ADMIN(Sets.newHashSet(CAR_CREATE,
            CAR_GET,
            CAR_DELETE,
            CAR_UPDATE,
            USER_GET,
            USER_DELETE,
            USER_UPDATE,
            RENT_GET,
            RENT_DELETE,
            RENT_UPDATE));

    private final Set<Permission> permissions;
    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
