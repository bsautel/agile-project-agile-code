package fr.fierdecoder.agilecode.example.springdata.after;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.UUID;

public class MongoDbUser {
    @Id
    private final UUID id;
    private final String firstName;
    private final String lastName;
    @Indexed
    private final String emailAddress;

    public MongoDbUser(UUID id, String firstName, String lastName, String emailAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public User toUser() {
        return new User(id, firstName, lastName, emailAddress);
    }
}
