package pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.managers.interfaces;

import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.VehicleType;

import javax.ejb.Local;
import java.util.List;

/**
 * Lokalny interfejs managera typów pojazdu
 *
 * @author Wojciech Sowa
 */
@Local
public interface VehicleTypeManagerLocal {

    /**
     * Metoda wyszukująca wszystkie typy pojazdów.
     *
     * @return Lista typów pojazdów {@link VehicleType}
     */
    List<VehicleType> getAllVehicleTypes();
}