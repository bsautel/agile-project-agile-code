package fr.fierdecoder.agilecode.example.springdata.before;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends MongoRepository<User, UUID> {
    Optional<User> findById(UUID id);

    User save(User user);

    List<User> findByEmailAddress(String mailAddress);
}
