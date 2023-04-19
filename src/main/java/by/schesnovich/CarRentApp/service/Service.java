package by.schesnovich.CarRentApp.service;

import by.schesnovich.CarRentApp.error.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static by.schesnovich.CarRentApp.error.message.ExceptionMessage.RESOURCE_NOT_FOUND_MSG;

@RequiredArgsConstructor
public abstract class Service<T, ID> {

    private final JpaRepository<T, ID> repository;

    public void create(T t) {
        repository.save(t);
    }

    public List<T> getAll() {
        return repository.findAll();
    }

    public abstract void update(ID id, T t);

    public void delete(ID id) {
        checkIfExists(id);
        repository.deleteById(id);
    }

    public T findById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(RESOURCE_NOT_FOUND_MSG, id)));
    }

    protected void checkIfExists(ID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(String.format(RESOURCE_NOT_FOUND_MSG, id));
        }
    }
}
