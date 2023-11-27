package spb.nicetu.OnlineElectronicsStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spb.nicetu.OnlineElectronicsStore.models.User;
import spb.nicetu.OnlineElectronicsStore.repositories.UserRepository;
import spb.nicetu.OnlineElectronicsStore.security.UserDetailsImpl;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User with email: '%s'", email)
        ));

        return new UserDetailsImpl(user);
    }
}
