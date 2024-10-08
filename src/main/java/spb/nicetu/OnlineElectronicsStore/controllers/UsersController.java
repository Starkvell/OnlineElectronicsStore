package spb.nicetu.OnlineElectronicsStore.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Users")
public class UsersController {

    private final UserService userServiceImpl;

    @Autowired
    public UsersController(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    /**
     * Получает информацию о текущем аутентифицированном пользователе.
     * @param userDetails Информация о текущем аутентифицированном пользователе. Внедряется Spring автоматически.
     * @return DTO пользователя при успешном запросе.
     */
    @Operation(
            summary = "Retrieves information about the currently authenticated user"
    )
    @GetMapping("/current")
    public UserDTO getCurrentUser(@AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();

        return UserMapper.MAPPER.toUserDTO(user);
    }


}
