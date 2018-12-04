package fr.fierdecoder.agilecode.example.solid.file.inversion;

import fr.fierdecoder.agilecode.example.solid.User;
import fr.fierdecoder.agilecode.example.solid.file.good.FileContentsReader;
import fr.fierdecoder.agilecode.example.solid.file.good.StringLinesReader;
import fr.fierdecoder.agilecode.example.solid.file.good.UserLineReader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class UsersReader {
    private final FileContentsReader fileContentsReader;
    private final StringLinesReader stringLinesReader;
    private final UserLineReader userLineReader;

    public UsersReader(FileContentsReader fileContentsReader, StringLinesReader stringLinesReader, UserLineReader userLineReader) {
        this.fileContentsReader = fileContentsReader;
        this.stringLinesReader = stringLinesReader;
        this.userLineReader = userLineReader;
    }

    public List<User> readUsers(Path usersFilePath) throws IOException {
        String fileContents = fileContentsReader.readFile(usersFilePath);
        String[] lines = stringLinesReader.readLines(fileContents);
        return Stream.of(lines)
                .map(userLineReader::readUser)
                .collect(toList());
    }
}
