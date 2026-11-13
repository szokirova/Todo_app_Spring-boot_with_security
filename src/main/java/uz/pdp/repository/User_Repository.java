package uz.pdp.repository;

import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import uz.pdp.entity.User;
import java.util.Optional;

public interface User_Repository extends CrudRepository<User,Integer> {
    Optional<User> findUserByUsername(@NonNull String username);

}