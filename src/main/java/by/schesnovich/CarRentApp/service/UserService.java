package by.schesnovich.CarRentApp.service;

import by.schesnovich.CarRentApp.entity.User;
import by.schesnovich.CarRentApp.error.exception.ResourceNotFoundException;
import by.schesnovich.CarRentApp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static by.schesnovich.CarRentApp.error.message.ExceptionMessage.USER_ALREADY_EXISTS_MSG;
import static by.schesnovich.CarRentApp.error.message.ExceptionMessage.USER_NOT_FOUND_MSG;

@org.springframework.stereotype.Service
public class UserService extends Service<User, Long> implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        super(repository);
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void create(User user) {
        checkIfExistsByUsername(user.getUsername());
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        repository.save(user);
    }

    @Override
    public void update(Long id, User user) {
        checkIfExists(id);
        user.setId(id);
        repository.save(user);
    }

    private void checkIfExistsByUsername(String username) {
        if (repository.existsByUsername(username)) {
            throw new ResourceNotFoundException(USER_ALREADY_EXISTS_MSG);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MSG));
    }
}
