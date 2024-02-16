package spb.nicetu.OnlineElectronicsStore.services;

import spb.nicetu.OnlineElectronicsStore.models.User;

public interface UserService {
    User findByEmail(String email);

    User findOne(int id);
}
