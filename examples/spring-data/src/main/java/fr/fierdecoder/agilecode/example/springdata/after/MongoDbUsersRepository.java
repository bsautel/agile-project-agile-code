package fr.fierdecoder.agilecode.example.springdata.after;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MongoDbUsersRepository extends MongoRepository<MongoDbUser, UUID> {
    Optional<MongoDbUser> findById(UUID id);

    MongoDbUser save(MongoDbUser user);

    List<MongoDbUser> findByEmailAddress(String mailAddress);
}
