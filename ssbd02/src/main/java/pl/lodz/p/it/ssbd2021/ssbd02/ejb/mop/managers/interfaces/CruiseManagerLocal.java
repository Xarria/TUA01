package pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.managers.interfaces;

import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Cruise;

import javax.ejb.Local;
import java.util.List;

/**
 * Lokalny interfejs managera rejsów
 *
 * @author Wojciech Sowa
 */
@Local
public interface CruiseManagerLocal {

    /**
     * Metoda wyszukująca wszystkie rejsy.
     *
     * @return Lista rejsów {@link Cruise}
     */
    List<Cruise> getAllCruises();

    /**
     * Metoda wyszukująca wszystkie aktualne rejsy.
     *
     * @return Lista aktualnych rejsów {@link Cruise}
     */
    List<Cruise> getAllCurrentCruises();

    /**
     * Metoda wyszukująca wszystkie zakończone rejsy.
     *
     * @return Lista zakończonych rejsów {@link Cruise}
     */
    List<Cruise> getAllCompletedCruises();

    /**
     * Metoda wyszukująca rejs o podanym numerze.
     *
     * @param number Numer rejsu, który chcemy wyszukać
     * @return Encja typu {@link Cruise}
     */
    Cruise getCruiseByNumber(String number);

    /**
     * Metoda wyszukująca wszystkie rejsy, które zawierają trasę o podanym kodzie.
     *
     * @param code Kod trasy, po której chcemy wyszukać
     * @return Lista rejsów {@link Cruise}, które zawierają trasę o podanym kodzie.
     */
    List<Cruise> getCruisesByRouteCode(String code);

    /**
     * Metoda wyszukująca wszystkie rejsy, które zawierają prom o podanej nazwie.
     *
     * @param name Nazwa promu, po której chcemy wyszukać
     * @return Lista rejsów {@link Cruise}, które zawierają prom o podanej nazwie.
     */
    List<Cruise> getCruisesByFerryName(String name);

    /**
     * Metoda tworząca rejs.
     *
     * @param cruise Encja typu {@link Cruise}
     */
    void createCruise(Cruise cruise);

    /**
     * Metoda aktualizuje rejs o numerze zawartym w encji {@link Cruise} oraz ustawia konto w polu modifiedBy na konto
     * użytkownika dokonującego zmiany.
     *
     * @param cruise     Encja typu {@link Cruise}
     * @param modifiedBy Login użytkownika, który edytuje encje
     */
    void updateCruise(Cruise cruise, String modifiedBy);

    /**
     * Metoda usuwa rejs o numerze zawartym w encji {@link Cruise}.
     *
     * @param cruise Encja typu {@link Cruise}
     */
    void removeCruise(Cruise cruise);
}