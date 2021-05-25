package pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.facades;

import pl.lodz.p.it.ssbd2021.ssbd02.ejb.AbstractFacade;
import pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.facades.interfaces.AccountMopFacadeLocal;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mok.Account;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Klasa rozszerzająca abstrakcyjną klasę {@link AbstractFacade}.
 * Używa konkretnego typu {@link Account} zamiast typu generycznego.
 * Jednostka składowania używana do wstrzyknięcia zarządcy encji to ssbd02mopPU.
 *
 * @author Karolina Kowalczyk
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@RolesAllowed({"DEFINITELY_NOT_A_REAL_ROLE"})
public class AccountMopFacade extends AbstractFacade<Account> implements AccountMopFacadeLocal {

    @PersistenceContext(unitName = "ssbd02mopPU")
    private EntityManager entityManager;

    public AccountMopFacade() {
        super(Account.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    @RolesAllowed({"EMPLOYEE", "CLIENT"})
    public Account findByLogin(String login) {
        TypedQuery<Account> typedQuery = entityManager.createNamedQuery("Account.findByLogin", Account.class);
        typedQuery.setParameter("login", login);
        return typedQuery.getSingleResult();
    }

    @Override
    @RolesAllowed({"EMPLOYEE", "CLIENT"})
    public Account findByEmail(String email) {
        TypedQuery<Account> typedQuery = entityManager.createNamedQuery("Account.findByEmail", Account.class);
        typedQuery.setParameter("email", email);
        return typedQuery.getSingleResult();
    }
}
