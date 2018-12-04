package fr.fierdecoder.agilecode.example.springdata.after;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UsersRepository {
    private final MongoDbUsersRepository mongoDbUsersRepository;

    public UsersRepository(MongoDbUsersRepository mongoDbUsersRepository) {
        this.mongoDbUsersRepository = mongoDbUsersRepository;
    }

    public List<User> findByEmailAddress(String emailAddress) {
        return mongoDbUsersRepository.findByEmailAddress(emailAddress).stream()
                .map(MongoDbUser::toUser)
                .collect(toList());
    }
}
