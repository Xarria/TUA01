package pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.managers.interfaces;

import org.apache.commons.lang3.tuple.Pair;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Cabin;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Cruise;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Ferry;

import javax.ejb.Local;
import java.util.List;

/**
 * Lokalny interfejs managera promów
 *
 * @author Wojciech Sowa
 */
@Local
public interface FerryManagerLocal {

    /**
     * Metoda wyszukująca wszystkie promy.
     *
     * @return Lista promów {@link Ferry}
     */
    List<Ferry> getAllFerries();

    /**
     * Metoda wyszukująca prom o podanej nazwie.
     *
     * @param name Nazwa promu, który chcemy wyszukać
     * @return Encja typu {@link Ferry}
     */
    Ferry getFerryByName(String name);

    /**
     * Metoda wyszukująca prom o podanej nazwie wraz z jego kajutami.
     *
     * @param name Nazwa promu, który chcemy wyszukać
     * @return Para reprezentująca prom, składająca się z klucza typu {@link Ferry} i wartości będącej listą obiektów typu {@link Cabin}
     */
    Pair<Ferry, List<Cabin>> getFerryAndCabinsByFerryName(String name);

    /**
     * Metoda tworząca prom.
     *
     * @param login Login użytkownika, który stworzył prom
     * @param ferry Encja typu {@link Ferry}
     */
    void createFerry(String login, Ferry ferry);

    /**
     * Metoda aktualizuje prom o nazwie zawartej w encji {@link Ferry} oraz ustawia konto w polu modifiedBy na konto
     * użytkownika dokonującego zmiany.
     *
     * @param ferry      Encja typu {@link Ferry}
     * @param modifiedBy Login użytkownika, który edytuje encje
     */
    void updateFerry(Ferry ferry, String modifiedBy);

    /**
     * Metoda usuwa prom o podanej nazwie.
     *
     * @param ferryName Nazwa promu, który ma zostać usunięty
     * @param login     Login użytkownika, który chce usunąć prom
     */
    void removeFerry(String ferryName, String login);

    /**
     * Metoda obliczająca popularność rejsu
     *
     * @param cruise Rejs, dla którego liczony jest agregat
     * @return Procentowa wartość popularności rejsu
     */
    double calculatePopularity(Cruise cruise);

    /**
     * Metoda zwracająca status transakcji.
     *
     * @return Status transakcji - true w przypadku jej powodzenia, false w przypadku jej wycofania
     */
    boolean isTransactionRolledBack();
}
