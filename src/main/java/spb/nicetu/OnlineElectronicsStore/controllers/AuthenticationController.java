package spb.nicetu.OnlineElectronicsStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spb.nicetu.OnlineElectronicsStore.dto.UserDTO;
import spb.nicetu.OnlineElectronicsStore.mappers.UserMapper;
import spb.nicetu.OnlineElectronicsStore.models.User;
import spb.nicetu.OnlineElectronicsStore.security.JWTUtil;
import spb.nicetu.OnlineElectronicsStore.services.AuthenticationService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final JWTUtil jwtUtil;
    private final UserMapper userMapper;

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(JWTUtil jwtUtil, UserMapper userMapper, AuthenticationService authenticationService) {
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/registration")
    public Map<String, String > performRegistration(@RequestBody @Valid UserDTO userDTO,
                                                    BindingResult bindingResult){
        User user = userMapper.convertToEntity(userDTO);

        //TODO: userValidator.validate(user, bindingResults);

        if (bindingResult.hasErrors()){
            throw new RuntimeException(); // TODO: exeption
        }

        authenticationService.register(user);

        String token = jwtUtil.generateToken(user.getEmail());
        return Collections.singletonMap("jwt-token", token);
    }
}
