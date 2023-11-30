package spb.nicetu.OnlineElectronicsStore.dto;

import lombok.*;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDTO {

    @Email(message = "Email должен быть действителен")
    private String email;

    private String password;
}
