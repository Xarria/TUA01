package pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.facades.interfaces;

import pl.lodz.p.it.ssbd2021.ssbd02.ejb.AbstractFacadeInterface;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Cabin;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Cruise;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Ferry;

import javax.ejb.Local;
import java.util.List;

/**
 * Interfejs encji modułu obsługi promów.
 * Używa konkretnego typu {@link Cabin} zamiast typu generycznego.
 * Jednostka składowania używana do wstrzyknięcia zarządcy encji do ssbd02mopPU.
 *
 * @author Daniel Łondka
 */
@Local
public interface CabinFacadeLocal extends AbstractFacadeInterface<Cabin> {

    /**
     * Metoda wyszukująca encje typu {@link Cabin} o przekazanym biznesowym numerze identyfikacyjnym.
     *
     * @param number Biznesowy numer identyfikacyjny.
     * @return Obiekt typu {@link Cabin} o przekazanym biznesowym numerze identyfikacyjnym.
     */
    Cabin findByNumber(String number);

    /**
     * Metoda wyszukująca encje typu {@link Cabin} o przekazanym biznesowym numerze identyfikacyjnym, na podanym promie.
     *
     * @param ferry       Encja typu {@link Ferry}.
     * @param cabinNumber Biznesowy numer identyfikacyjny kajutę.
     * @return Obiekt typu {@link Cabin} o przekazanym biznesowym numerze identyfikacyjnym, znajdująca się na podanym promie.
     */
    Cabin findByFerryAndNumber(Ferry ferry, String cabinNumber);


    /**
     * Metoda wyszukująca listę encji typu {@link Cabin}, które są na danym promie.
     *
     * @param ferry Encja typu {@link Ferry}.
     * @return Listę obiektów typu {@link Cabin}, które są na danym promie.
     */
    List<Cabin> findAllByFerry(Ferry ferry);

    /**
     * Metoda wyszukująca listę encji typu {@link Cabin}, które są zajęte na danym rejsie.
     *
     * @param cruise Encja typu {@link Cruise}.
     * @return Listę obiektów typu {@link Cabin}, które są zajęte na danym rejsie.
     */
    List<Cabin> findOccupiedCabinsOnCruise(Cruise cruise);

    /**
     * Metoda wyszukująca listę encji typu {@link Cabin} na danym rejsie.
     *
     * @param cruise Encja typu {@link Cruise}.
     * @return Listę obiektów typu {@link Cabin} na danym rejsie.
     */
    List<Cabin> findCabinsOnCruise(Cruise cruise);
}
