package fr.fierdecoder.agilecode.example.solid.file.bad;

import fr.fierdecoder.agilecode.example.solid.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class UsersReaderTest {
    private UsersReader usersReader;

    @Before
    public void setUp() {
        usersReader = new UsersReader();
    }

    @Test
    public void shouldReadUsers() throws URISyntaxException, IOException {
        Path usersFilePath = Paths.get(getClass().getClassLoader().getResource("users.txt").toURI());

        List<User> users = usersReader.readUsers(usersFilePath);

        assertThat(users).containsOnly(
                new User(UUID.fromString("4ffb288d-6444-49f1-bc50-c9c7ac772757"), "Bob", "Martin", "bob@cleancode.com"),
                new User(UUID.fromString("18705afe-f896-46b6-b157-6b33a3efb189"), "Alice", "Martin", "alice@badcode.com")
        );
    }
}