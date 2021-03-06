package pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.facades;

import pl.lodz.p.it.ssbd2021.ssbd02.ejb.AbstractFacade;
import pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.facades.interfaces.FerryFacadeLocal;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Ferry;
import pl.lodz.p.it.ssbd2021.ssbd02.utils.interceptors.GeneralInterceptor;
import pl.lodz.p.it.ssbd2021.ssbd02.utils.interceptors.PersistenceInterceptor;
import pl.lodz.p.it.ssbd2021.ssbd02.utils.interceptors.TrackerInterceptor;
import pl.lodz.p.it.ssbd2021.ssbd02.utils.interceptors.mop.FerryInterceptor;

import javax.annotation.security.DenyAll;
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
 * Używa konkretnego typu {@link Ferry} zamiast typu generycznego.
 * Jednostka składowania używana do wstrzyknięcia zarządcy encji do ssbd02mopPU.
 *
 * @author Artur Madaj
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@RolesAllowed({"DEFINITELY_NOT_A_REAL_ROLE"})
@Interceptors({GeneralInterceptor.class, FerryInterceptor.class, PersistenceInterceptor.class, TrackerInterceptor.class})
public class FerryFacade extends AbstractFacade<Ferry> implements FerryFacadeLocal {

    @PersistenceContext(unitName = "ssbd02mopPU")
    private EntityManager entityManager;

    public FerryFacade() {
        super(Ferry.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    @RolesAllowed({"EMPLOYEE", "CLIENT"})
    public Ferry findByName(String name) {
        TypedQuery<Ferry> typedQuery = entityManager.createNamedQuery("Ferry.findByName", Ferry.class);
        typedQuery.setParameter("name", name);
        return typedQuery.getSingleResult();
    }

    @Override
    @RolesAllowed({"EMPLOYEE"})
    public void create(Ferry entity) {
        super.create(entity);
    }

    @Override
    @DenyAll
    public Ferry find(Object id) {
        return super.find(id);
    }

    @Override
    @RolesAllowed({"EMPLOYEE"})
    public void edit(Ferry entity) {
        super.edit(entity);
    }

    @Override
    @RolesAllowed({"EMPLOYEE"})
    public void remove(Ferry entity) {
        super.remove(entity);
    }

    @Override
    @RolesAllowed({"EMPLOYEE"})
    public List<Ferry> findAll() {
        return super.findAll();
    }

    @Override
    @DenyAll
    public List<Ferry> findInRange(int start, int end) {
        return super.findInRange(start, end);
    }

    @Override
    @DenyAll
    public int count() {
        return super.count();
    }

    @Override
    @RolesAllowed({"EMPLOYEE"})
    public List<Ferry> findWithNamedQuery(String namedQuery) {
        return super.findWithNamedQuery(namedQuery);
    }

    @Override
    @DenyAll
    public List<Ferry> findWithQuery(String query) {
        return super.findWithQuery(query);
    }
}
