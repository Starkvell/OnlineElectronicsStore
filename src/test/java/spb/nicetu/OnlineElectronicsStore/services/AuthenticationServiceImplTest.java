package spb.nicetu.OnlineElectronicsStore.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import spb.nicetu.OnlineElectronicsStore.models.User;
import spb.nicetu.OnlineElectronicsStore.repositories.UserRepository;
import spb.nicetu.OnlineElectronicsStore.services.impl.AuthenticationServiceImpl;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationServiceImpl authenticationServiceImpl;

    @Test
    void testRegister_SuccessfulRegistration() {
        // Arrange
        User user = new User("first name","last name", "email@email.ru", "password");
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        // Act
        authenticationServiceImpl.register(user);

        // Assert
        verify(passwordEncoder, times(1)).encode("password");
        verify(userRepository, times(1)).save(argThat(savedUser ->
                savedUser.getEmail().equals("email@email.ru") &&
                        savedUser.getPassword().equals("encodedPassword")));
    }
}