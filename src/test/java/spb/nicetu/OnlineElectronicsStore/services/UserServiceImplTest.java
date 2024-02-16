package spb.nicetu.OnlineElectronicsStore.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spb.nicetu.OnlineElectronicsStore.models.User;
import spb.nicetu.OnlineElectronicsStore.repositories.UserRepository;
import spb.nicetu.OnlineElectronicsStore.services.impl.UserServiceImpl;
import spb.nicetu.OnlineElectronicsStore.util.exceptions.UserNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void testFindByEmail_Successful() {
        // Arrange
        User user = new User("Firstname", "Lastname", "email@mail.ru", "password");

        when(userRepository.findByEmail("email@mail.ru")).thenReturn(Optional.of(user));

        // Act
        User result = userServiceImpl.findByEmail("email@mail.ru");

        // Assert
        assertThat(result).isEqualTo(user);
    }

    @Test
    void testFindByEmail_UserNotFound(){
        // Arrange
        String email = "email@mail.ru";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act and Assert
        assertThatThrownBy(() -> userServiceImpl.findByEmail(email))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("User with email: '" + email + "' not found");
    }

    @Test
    void testFindOne_Successful() {
        // Arrange
        User user = new User("Firstname", "Lastname", "email@mail.ru", "password");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        // Act
        User result = userServiceImpl.findOne(1);

        // Assert
        assertThat(result).isEqualTo(user);
    }

    @Test
    void testFindOne_UserNotFound() {
        // Arrange
        int userId = 999;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThatThrownBy(() -> userServiceImpl.findOne(userId))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("Could not find object user with ID:" + userId);
    }
}