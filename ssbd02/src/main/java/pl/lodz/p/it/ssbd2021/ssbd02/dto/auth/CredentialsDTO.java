package pl.lodz.p.it.ssbd2021.ssbd02.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;

/**
 * Klasa DTO zawierająca dane uwierzytelniające użytkownika.
 * Używana przy odbieraniu danych przez punkt dostępowy obsługujący uwierzytelnianie.
 *
 * @author Kacper Świercz
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsDTO {

    @NotBlank
    @Size(max = 30)
    private String login;

    @NotBlank
    @Size(min = 8)
    @ToString.Exclude
    private String password;
}
