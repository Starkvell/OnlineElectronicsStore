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
    private final UserMapper userMapper;

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationController(JWTUtil jwtUtil, UserMapper userMapper, AuthenticationService authenticationService, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> performRegistration(@RequestBody @Valid UserDTO userDTO,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        User user = userMapper.convertToEntity(userDTO);
        authenticationService.register(user);

        String token = jwtUtil.generateToken(user.getEmail());
        Map<String, String> response = Collections.singletonMap("jwt-token", token);

        return ResponseEntity.ok(response);
    }

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
