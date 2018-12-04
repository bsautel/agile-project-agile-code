package fr.fierdecoder.agilecode.example.solid.file.good;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileContentsReader {
    public String readFile(Path filePath) throws IOException {
        byte[] encoded = Files.readAllBytes(filePath);
        return new String(encoded, UTF_8);
    }
}
