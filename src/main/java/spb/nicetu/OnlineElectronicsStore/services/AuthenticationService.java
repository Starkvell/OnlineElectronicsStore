package spb.nicetu.OnlineElectronicsStore.services;

import spb.nicetu.OnlineElectronicsStore.models.User;

import javax.transaction.Transactional;

public interface AuthenticationService {
    @Transactional
    void register(User user);
}
