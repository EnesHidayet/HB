package org.enes.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.enes.entity.Secmen;

import java.util.List;
import java.util.Optional;

public class RepositoryManager<T,ID> implements ICrud<T,ID>{
    private EntityManagerFactory emf;
    private EntityManager em;
    private final T t;

    public RepositoryManager(T t){
        emf = Persistence.createEntityManagerFactory("CRM");
        em=emf.createEntityManager();
        this.t=t;
    }
    @Override
    public T save(T entity) {
        return null;
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> entity) {
        return null;
    }

    @Override
    public Optional<T> findById(ID id) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
        Root<T> root = (Root<T>) criteriaQuery.from(t.getClass());
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),id));
        try {
            T t1 = em.createQuery(criteriaQuery).getSingleResult();
            return Optional.of(t1);
        }catch (NoResultException e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }

    }

    @Override
    public boolean existById(ID id) {
        return false;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public List<T> findByColumnAndValue(String columnName, Object value) {
        return null;
    }

    @Override
    public void deleteById(ID id) {

    }

    @Override
    public List<T> findAllEntity(T entity) {
        return null;
    }
}
