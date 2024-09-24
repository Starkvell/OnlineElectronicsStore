package spb.nicetu.OnlineElectronicsStore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spb.nicetu.OnlineElectronicsStore.models.User;
import spb.nicetu.OnlineElectronicsStore.repositories.UserRepository;
import spb.nicetu.OnlineElectronicsStore.services.UserService;
import spb.nicetu.OnlineElectronicsStore.util.exceptions.UserNotFoundException;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElseThrow(() -> new UserNotFoundException(
                String.format("User with email: '%s' not found", email)
        ));
    }

    @Override
    public User findOne(int id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElseThrow(() -> new UserNotFoundException(id));
    }
}
