package pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.facades;

import pl.lodz.p.it.ssbd2021.ssbd02.ejb.AbstractFacade;
import pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.facades.interfaces.SeaportFacadeLocal;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Seaport;
import pl.lodz.p.it.ssbd2021.ssbd02.utils.interceptors.GeneralInterceptor;
import pl.lodz.p.it.ssbd2021.ssbd02.utils.interceptors.PersistenceInterceptor;
import pl.lodz.p.it.ssbd2021.ssbd02.utils.interceptors.mop.SeaportInterceptor;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Klasa rozszerzająca abstrakcyjną klasę {@link AbstractFacade}.
 * Używa konkretnego typu {@link Seaport} zamiast typu generycznego.
 * Jednostka składowania używana do wstrzyknięcia zarządcy encji do ssbd02mopPU.
 *
 * @author Artur Madaj
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors({GeneralInterceptor.class, SeaportInterceptor.class, PersistenceInterceptor.class})
public class SeaportFacade extends AbstractFacade<Seaport> implements SeaportFacadeLocal {

    @PersistenceContext(unitName = "ssbd02mopPU")
    private EntityManager entityManager;

    public SeaportFacade() {
        super(Seaport.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public Seaport findByCode(String code) {
        TypedQuery<Seaport> typedQuery = entityManager.createNamedQuery("Seaport.findByCode", Seaport.class);
        typedQuery.setParameter("code", code);
        return typedQuery.getSingleResult();
    }
}
