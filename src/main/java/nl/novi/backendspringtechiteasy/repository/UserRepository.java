package nl.novi.backendspringtechiteasy.repository;

import nl.novi.backendspringtechiteasy.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
