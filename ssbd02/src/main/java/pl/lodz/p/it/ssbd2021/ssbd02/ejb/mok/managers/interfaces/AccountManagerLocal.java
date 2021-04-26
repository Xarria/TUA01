package pl.lodz.p.it.ssbd2021.ssbd02.ejb.mok.managers.interfaces;

import pl.lodz.p.it.ssbd2021.ssbd02.entities.mok.AccessLevel;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mok.Account;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

/**
 * Lokalny interfejs managera kont
 *
 * @author Daniel Łondka
 */
@Local
public interface AccountManagerLocal {

    /**
     * Metoda wyszukujące wszystkie konta wraz z ich poziomami dostępu
     *
     * @return lista obiektów typu {@link Account} reprezentujących konta
     */
    Map<Account, List<AccessLevel>> getAllAccountsWithAccessLevels();
}
