package pl.lodz.p.it.ssbd2021.ssbd02.dto.mop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Klasa DTO zawierająca typ pojazdu.
 *
 * @author Artur Madaj
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VehicleTypeDTO {

    private String vehicleTypeName;

    private Double requiredSpace;
}
