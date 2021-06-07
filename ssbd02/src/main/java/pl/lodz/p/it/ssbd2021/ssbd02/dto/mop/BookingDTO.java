package pl.lodz.p.it.ssbd2021.ssbd02.dto.mop;

import lombok.*;
import pl.lodz.p.it.ssbd2021.ssbd02.dto.AbstractDTO;
import pl.lodz.p.it.ssbd2021.ssbd02.dto.mok.AccountGeneralDTO;

import java.sql.Timestamp;

/**
 * Klasa DTO zawierająca rezerwacje.
 *
 * @author Artur Madaj
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class BookingDTO extends AbstractDTO {

    private CruiseDTO cruise;

    private AccountGeneralDTO account;

    private Integer numberOfPeople;

    private CabinDetailsDTO cabin;

    private VehicleTypeDTO vehicleType;

    private Double price;

    private String number;

    private Timestamp creationDate;
}
