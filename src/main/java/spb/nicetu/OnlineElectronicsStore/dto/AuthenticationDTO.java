package spb.nicetu.OnlineElectronicsStore.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDTO {

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;
}
