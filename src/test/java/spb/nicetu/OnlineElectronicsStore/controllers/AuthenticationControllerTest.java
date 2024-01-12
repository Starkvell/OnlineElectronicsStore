package spb.nicetu.OnlineElectronicsStore.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MockMvc;
import spb.nicetu.OnlineElectronicsStore.dto.AuthenticationDTO;
import spb.nicetu.OnlineElectronicsStore.dto.UserDTO;
import spb.nicetu.OnlineElectronicsStore.mappers.UserMapper;
import spb.nicetu.OnlineElectronicsStore.models.User;
import spb.nicetu.OnlineElectronicsStore.security.JWTUtil;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JWTUtil jwtUtil;

    @MockBean
    private AuthenticationManager authenticationManager;


    @Test
    void testPerformRegistration_Success() throws Exception {
        // Arrange

        UserDTO userDTO = new UserDTO("John", "Doe", "john.doe@example.com", "password");
        User user = UserMapper.MAPPER.toUser(userDTO);


        when(jwtUtil.generateToken(user.getEmail())).thenReturn("fakeToken");

        // Act & Assert
        mockMvc.perform(post("/auth/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDTO))).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt-token").value("fakeToken"));
    }

    @Test
    public void testPerformRegistration_ValidationFailure() throws Exception {
        // Arrange
        UserDTO userDTO = new UserDTO("John", "Doe", "invalidEmail", "password");


        // Act & Assert
        mockMvc.perform(post("/auth/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].field").value("email"))
                .andExpect(jsonPath("$[0].defaultMessage").value("Invalid email address"));
    }

    @Test
    public void testPerformLogin_Success() throws Exception {
        // Arrange
        AuthenticationDTO authenticationDTO = new AuthenticationDTO("john.doe@example.com", "password");

        when(jwtUtil.generateToken(authenticationDTO.getEmail())).thenReturn("fakeToken");

        // Act & Assert
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(authenticationDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt-token").value("fakeToken"));
    }

    @Test
    public void testPerformLogin_InvalidCredentials() throws Exception {
        // Arrange
        AuthenticationDTO authenticationDTO = new AuthenticationDTO("invalidUser@example.com", "invalidPassword");

        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        // Act & Assert
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(authenticationDTO)))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Incorrect credentials"));
    }

    private static String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}