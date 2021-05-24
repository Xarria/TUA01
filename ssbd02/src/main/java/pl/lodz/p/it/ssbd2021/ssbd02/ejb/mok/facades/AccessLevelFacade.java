package pl.lodz.p.it.ssbd2021.ssbd02.ejb.mok.facades;

import pl.lodz.p.it.ssbd2021.ssbd02.ejb.AbstractFacade;
import pl.lodz.p.it.ssbd2021.ssbd02.ejb.mok.facades.interfaces.AccessLevelFacadeLocal;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mok.AccessLevel;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mok.Account;
import pl.lodz.p.it.ssbd2021.ssbd02.utils.interceptors.GeneralInterceptor;
import pl.lodz.p.it.ssbd2021.ssbd02.utils.interceptors.PersistenceInterceptor;
import pl.lodz.p.it.ssbd2021.ssbd02.utils.interceptors.mok.AccessLevelInterceptor;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Klasa rozszerzająca abstrakcyjną klasę {@link AbstractFacade}.
 * Używa konkretnego typu {@link AccessLevel} zamiast typu generycznego.
 * Jednostka składowania używana do wstrzyknięcia zarządcy encji to ssbd02mokPU.
 *
 * @author Patryk Kolanek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors({GeneralInterceptor.class, AccessLevelInterceptor.class, PersistenceInterceptor.class})
@RolesAllowed({"DEFINITELY_NOT_A_REAL_ROLE"})
public class AccessLevelFacade extends AbstractFacade<AccessLevel> implements AccessLevelFacadeLocal {

    @PersistenceContext(unitName = "ssbd02mokPU")
    private EntityManager entityManager;

    public AccessLevelFacade() {
        super(AccessLevel.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<AccessLevel> findByLogin(String login) {
        TypedQuery<AccessLevel> typedQuery = entityManager.createNamedQuery("AccessLevel.findByLogin", AccessLevel.class);
        typedQuery.setParameter("login", login);
        return typedQuery.getResultList();
    }

    @Override
    @RolesAllowed({"ADMIN", "EMPLOYEE", "CLIENT"})
    public List<AccessLevel> findAllByAccount(Account account) {
        TypedQuery<AccessLevel> typedQuery = entityManager.createNamedQuery("AccessLevel.findByAccount", AccessLevel.class);
        typedQuery.setParameter("account", account);
        return typedQuery.getResultList();
    }

    @Override
    @RolesAllowed({"ADMIN", "EMPLOYEE", "CLIENT"})
    public List<AccessLevel> findAllActiveByAccount(Account account) {
        TypedQuery<AccessLevel> typedQuery = entityManager.createNamedQuery("AccessLevel.findAllActiveByAccount", AccessLevel.class);
        typedQuery.setParameter("account", account);
        return typedQuery.getResultList();
    }
}
