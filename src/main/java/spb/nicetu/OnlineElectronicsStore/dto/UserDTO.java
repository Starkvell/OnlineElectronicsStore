package spb.nicetu.OnlineElectronicsStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spb.nicetu.OnlineElectronicsStore.annotations.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 100, message = "The name must be between 2 and 100 characters")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 100, message = "The last name must be between 2 and 100 characters")
    private String lastName;

    @Email(message = "Invalid email address")
    @UniqueEmail
    @NotBlank(message = "Email is required")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 6, max = 50, message = "Password must be at least 6 characters long")
    private String password;


}
