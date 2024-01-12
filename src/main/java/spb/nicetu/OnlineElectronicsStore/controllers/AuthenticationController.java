package spb.nicetu.OnlineElectronicsStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spb.nicetu.OnlineElectronicsStore.dto.AuthenticationDTO;
import spb.nicetu.OnlineElectronicsStore.dto.UserDTO;
import spb.nicetu.OnlineElectronicsStore.mappers.UserMapper;
import spb.nicetu.OnlineElectronicsStore.models.User;
import spb.nicetu.OnlineElectronicsStore.security.JWTUtil;
import spb.nicetu.OnlineElectronicsStore.services.AuthenticationService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final JWTUtil jwtUtil;

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationController(JWTUtil jwtUtil, AuthenticationService authenticationService, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Регистрирует нового пользователя в системе и возвращает JWT-токен для аутентификации.
     *
     *
     * @param userDTO Данные пользователя для регистрации.
     * @param bindingResult Представляет результаты привязки в процессе валидации. Внедряется автоматически Spring.
     * @return ResponseEntity с JWT-токеном при успешной регистрации или
     * ответом с ошибкой Bad Request с сообщениями о валидации, если входные данные недопустимы.
     *
     */
    @PostMapping("/registration")
    public ResponseEntity<?> performRegistration(@RequestBody @Valid UserDTO userDTO,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        User user = UserMapper.MAPPER.toUser(userDTO);
        authenticationService.register(user); // TODO: Сделать возвращаемое значение User, передавать DTO

        String token = jwtUtil.generateToken(user.getEmail());
        Map<String, String> response = Collections.singletonMap("jwt-token", token);

        return ResponseEntity.ok(response);
    }

    /**
     * Обрабатывает процесс аутентификации пользователя, создавая новый JWT-токен при успешном входе в систему.
     * @param authenticationDTO Данные аутентификации пользователя.
     * @return ResponseEntity с JWT-токеном при успешной аутентификации или
     *         ответом с ошибкой Unauthorized в случае неверных учетных данных.
     */
    @PostMapping("/login")
    public ResponseEntity<?> performLogin(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(), authenticationDTO.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Incorrect credentials"), HttpStatus.UNAUTHORIZED);
        }

        String token = jwtUtil.generateToken(authenticationDTO.getEmail());
        return ResponseEntity.ok(Collections.singletonMap("jwt-token", token));
    }
}
