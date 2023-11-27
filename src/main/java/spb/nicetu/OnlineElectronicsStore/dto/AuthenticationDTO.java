package spb.nicetu.OnlineElectronicsStore.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class AuthenticationDTO {

    @Email(message = "Email должен быть действителен")
    private String email;

    private String password;
}
