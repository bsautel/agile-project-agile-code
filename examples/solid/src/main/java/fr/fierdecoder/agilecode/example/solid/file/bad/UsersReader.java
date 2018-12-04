package fr.fierdecoder.agilecode.example.solid.file.bad;

import fr.fierdecoder.agilecode.example.solid.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.toList;

public class UsersReader {
    public List<User> readUsers(Path usersFilePath) throws IOException {
        byte[] encoded = Files.readAllBytes(usersFilePath);
        String fileContents = new String(encoded, UTF_8);
        return Stream.of(fileContents.split("\n"))
                .map(line -> {
                    String[] lineParts = line.split(";");
                    return new User(UUID.fromString(lineParts[0]), lineParts[1], lineParts[2], lineParts[3]);
                })
                .collect(toList());
    }
}
