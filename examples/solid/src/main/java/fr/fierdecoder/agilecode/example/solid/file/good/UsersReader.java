package fr.fierdecoder.agilecode.example.solid.file.good;

import fr.fierdecoder.agilecode.example.solid.User;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class UsersReader {
    private final FileContentsReader fileContentsReader = new FileContentsReader();
    private final StringLinesReader stringLinesReader = new StringLinesReader();
    private final UserLineReader userLineReader = new UserLineReader();

    public List<User> readUsers(Path usersFilePath) throws IOException {
        String fileContents = fileContentsReader.readFile(usersFilePath);
        String[] lines = stringLinesReader.readLines(fileContents);
        return Stream.of(lines)
                .map(userLineReader::readUser)
                .collect(toList());
    }
}
