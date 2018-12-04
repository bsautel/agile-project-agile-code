package fr.fierdecoder.agilecode.example.solid.file.good;

import fr.fierdecoder.agilecode.example.solid.User;

import java.util.UUID;

public class UserLineReader {
    public User readUser(String line) {
        String[] lineParts = line.split(";");
        return new User(UUID.fromString(lineParts[0]), lineParts[1], lineParts[2], lineParts[3]);
    }
}
