package spb.nicetu.OnlineElectronicsStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spb.nicetu.OnlineElectronicsStore.dto.UserDTO;
import spb.nicetu.OnlineElectronicsStore.mappers.UserMapper;
import spb.nicetu.OnlineElectronicsStore.models.User;
import spb.nicetu.OnlineElectronicsStore.security.UserDetailsImpl;
import spb.nicetu.OnlineElectronicsStore.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Получает информацию о текущем аутентифицированном пользователе.
     * @param userDetails Информация о текущем аутентифицированном пользователе. Внедряется Spring автоматически.
     * @return DTO пользователя при успешном запросе.
     */
    @GetMapping("/current")
    public UserDTO getCurrentUser(@AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();

        return UserMapper.MAPPER.toUserDTO(user);
    }


}
