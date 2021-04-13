package pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.facades;

import pl.lodz.p.it.ssbd2021.ssbd02.ejb.AbstractFacade;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Booking;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Klasa rozszerzająca abstrakcyjną klasę {@link AbstractFacade}.
 * Używa konkretnego typu {@link Booking} zamiast typu generycznego.
 * Jednostka składowania używana do wstrzyknięcia zarządcy encji do ssbd02mopPU.
 *
 * @author Artur Madaj
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class BookingFacade extends AbstractFacade<Booking> {

    @PersistenceContext(unitName = "ssbd02mopPU")
    private EntityManager entityManager;

    public BookingFacade() {
        super(Booking.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Metoda wyszukująca encje typu {@link Booking} o przekazanym biznesowym numerze identyfikacyjnym.
     *
     * @param number Biznesowy numer identyfikacyjny.
     * @return Obiekt typu {@link Booking} o przekazanym biznesowym numerze identyfikacyjnym.
     */
    public Booking findByNumber(String number) {
        TypedQuery<Booking> typedQuery = entityManager.createNamedQuery("Booking.findByNumber", Booking.class);
        typedQuery.setParameter("number", number);
        return typedQuery.getSingleResult();
    }
}