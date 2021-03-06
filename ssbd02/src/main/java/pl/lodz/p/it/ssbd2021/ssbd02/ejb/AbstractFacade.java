package pl.lodz.p.it.ssbd2021.ssbd02.ejb;

import lombok.AllArgsConstructor;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Klasa abstrakcyjna implementująca podstawowe operacje na encjach
 *
 * @param <T>
 * @author Julia Kołodziej
 */

@AllArgsConstructor
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public abstract class AbstractFacade<T> {

    private final Class<T> entityClass;

    protected abstract EntityManager getEntityManager();

    /**
     * Metoda odpowiadająca ze dołączenie encji do kontekstu utrwalania i przejście encji w stan zarządzania
     *
     * @param entity Encja typu T
     */
    public void create(T entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
    }

    /**
     * Metoda wyszukująca encję danej klasy o danej wartości klucza głównego
     *
     * @param id Klucz główny
     * @return Encja typu T w przypadku zgodności wartości klucza głównego,
     * null gdy brak encji o podanej wartości klucza głównego
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * Metoda synchronizująca stan obiektu w kontekście utrwalania, odpowiada za utworzenie kopii przekazanej encji,
     * i dołączenie jej do kontekstu utrwalania
     *
     * @param entity Encja typu T
     */
    public void edit(T entity) {
        getEntityManager().merge(entity);
        getEntityManager().flush();
    }

    /**
     * Metoda usuwająca encję
     *
     * @param entity Encja typu T
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        getEntityManager().flush();
    }

    /**
     * Metoda wyszukująca wszystkie encje danego typu
     *
     * @return Kolekcja wszystkich encji typu T
     */
    public List<T> findAll() {
        CriteriaQuery<T> criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    /**
     * Metoda pobierająca encje danego typu z podanego przedziału
     *
     * @param start Początek przedziału
     * @param end   Koniec przedziału (włącznie)
     * @return Kolekcja encji typu T z przekazanego przedziału
     */
    public List<T> findInRange(int start, int end) {
        CriteriaQuery<T> criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        TypedQuery<T> query = getEntityManager().createQuery(criteriaQuery);
        query.setFirstResult(start).setMaxResults(end - start + 1);
        return query.getResultList();
    }

    /**
     * Metoda zwracająca liczbę wszystkich encji danego typu
     *
     * @return Liczba wszystkich encji typu T
     */
    public int count() {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(criteriaBuilder.count(root));
        return (getEntityManager().createQuery(criteriaQuery).getSingleResult()).intValue();
    }

    /**
     * Metoda wyszukująca encje na podstawie przekazanego zapytania nazwanego
     *
     * @param namedQuery Zapytanie nazwane
     * @return Kolekcja encji typu T
     */
    public List<T> findWithNamedQuery(String namedQuery) {
        return getEntityManager().createNamedQuery(namedQuery, entityClass).getResultList();
    }

    /**
     * Metoda wyszukująca encje na podstawie przekazanego zapytania
     *
     * @param query Zapytanie
     * @return Kolekcja encji typu T
     */
    public List<T> findWithQuery(String query) {
        return getEntityManager().createQuery(query, entityClass).getResultList();
    }
}
