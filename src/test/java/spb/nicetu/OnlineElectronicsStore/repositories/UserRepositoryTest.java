package spb.nicetu.OnlineElectronicsStore.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import spb.nicetu.OnlineElectronicsStore.models.User;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User(1, "Sergey", "Drozdov",
                "drozdov@mail.ru", "123456", null, null);
        userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        testUser = null;
        userRepository.deleteAll();
    }

    @Test
    void testFindByEmail_Found() {
        Optional<User> found = userRepository.findByEmail("drozdov@mail.ru");
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getEmail()).isEqualTo("drozdov@mail.ru");
    }

    @Test
    void testFindByEmail_NotFound() {
        Optional<User> found = userRepository.findByEmail("drozdov");
        assertThat(found.isPresent()).isFalse();
    }
}
