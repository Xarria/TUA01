package pl.lodz.p.it.ssbd2021.ssbd02.dto.mok;

import lombok.*;
import pl.lodz.p.it.ssbd2021.ssbd02.dto.AbstractDTO;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Klasa DTO zawierająca dane konta użytkownika.
 * Używana przy wyświetlaniu szczegółów konta użytkownika.
 *
 * @author Karolina Kowalczyk
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class AccountDetailsDTO extends AbstractDTO {

    @NotBlank
    @Size(max = 30, message = "First name must have less than 30 characters")
    private String login;

    @Size(min = 8, message = "Password must not have less than 8 characters")
    private String password;

    private Boolean active;

    private Boolean confirmed;

    @Size(max = 30, message = "First name must have less than 30 characters")
    private String firstName;

    @Size(max = 50, message = "Last name must have less than 50 characters")
    private String lastName;

    @Email
    @Size(max = 70, message = "Email must have less than 70 characters")
    private String email;

    @Size(min = 3, max = 15, message = "Phone number must have between 3 and 15 characters")
    private String phoneNumber;

    private List<AccessLevelDTO> accessLevel;

    @Pattern(regexp = "[a-z]{2}|[a-z]{2}-[A-Z]{2}", message = "Language must be 2 lower case letters or 2 lower case letters, a \"-\" and 2 capital letters")
    private String language;

    @Pattern(regexp = "[+-]0[0-9]:00|-1[0-2]:00|[+]1[0-4]:00", message = "Invalid time zone format, should be like \"+\\-00:00\"")
    private String timeZone;

    private Timestamp modificationDate;

    private AccountGeneralDTO modifiedBy;

    private Timestamp activityModificationDate;

    private AccountGeneralDTO activityModifiedBy;

    private Timestamp confirmedModificationDate;

    private Timestamp passwordModificationDate;

    private Timestamp emailModificationDate;

    private Timestamp creationDate;

    private Timestamp lastKnownGoodLogin;

    @Size(max = 39)
    private String lastKnownGoodLoginIp;

    private Timestamp lastKnownBadLogin;

    @Size(max = 39)
    private String lastKnownBadLoginIp;

    @Min(value = 0)
    @Max(value = 3)
    private int numberOfBadLogins;

    @JsonbTransient
    public String getPassword() {
        return password;
    }

    @JsonbProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
