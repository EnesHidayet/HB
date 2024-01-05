package org.enes.repository;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.enes.entity.Secmen;
import org.enes.utility.view.VwSecmen;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SecmenRepository {

    private EntityManagerFactory emf;
    private EntityManager manager;
    public SecmenRepository(){
        emf = Persistence.createEntityManagerFactory("CRM");
        manager = emf.createEntityManager();
    }

    public void save(Secmen secmen){
        manager.getTransaction().begin();
        manager.persist(secmen);
        manager.getTransaction().commit();
    }

    public List<Secmen> findAll(){
        /**
         * select * from tbl_secmen
         * criteria -> sizin sorgularınızı tanımlamak için kullanılır.
         * root -> entity içindeki alanları özel olarak belirlemek için kullanılır.
         */
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Secmen> criteriaQuery = builder.createQuery(Secmen.class); // hangi entity üzerinde işlem yapacağımızı belirtiyoruz.

        Root<Secmen> root = criteriaQuery.from(Secmen.class); //select *
        criteriaQuery.select(root); // select * from tbl_secmen

        List<Secmen> result = manager.createQuery(criteriaQuery).getResultList();
        return result;
    }

    public List<String> getAllTcKimlik(){
        /**
         * select tckimlik from tbl_secmen
         */
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);

        Root<Secmen> root = criteriaQuery.from(Secmen.class);
        criteriaQuery.select(root.get("tckimlik")); // select tckimlik from tbl_secmen

        List<String> tcList = manager.createQuery(criteriaQuery).getResultList();
        return tcList;
    }

    public Optional<Secmen> findById(Long id){
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Secmen> criteriaQuery = criteriaBuilder.createQuery(Secmen.class);
        Root<Secmen> root = criteriaQuery.from(Secmen.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),id));
        try {
            Secmen secmen = manager.createQuery(criteriaQuery).getSingleResult();
            return Optional.of(secmen);
        }catch (NoResultException e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public List<Object[]> getTcKimlikAndName(){
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Secmen> root = criteriaQuery.from(Secmen.class);
        criteriaQuery.select(criteriaBuilder.array(root.get("tckimlik"), root.get("ad")));
        List<Object[]> results = manager.createQuery(criteriaQuery).getResultList();

        return results;
    }

    /**
     * Using Tuple
     */
    public List<VwSecmen> getAllViewSecmen(){
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<Secmen> root = criteriaQuery.from(Secmen.class);
        Path<String> tckimlik=root.get("tckimlik");
        Path<String> ad=root.get("ad");
        criteriaQuery.multiselect(tckimlik,ad);
        List<Tuple> tuples = manager.createQuery(criteriaQuery).getResultList();

        List<VwSecmen> result = new ArrayList<>();
        tuples.forEach(t-> {
            result.add(new VwSecmen(t.get(ad),t.get(tckimlik)));
        });

        return result;
    }

    /**
     * Group By
     */

    public List<Object[]> getAdCount(){
        CriteriaBuilder criteriaBuilder=manager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

        Root<Secmen> root = criteriaQuery.from(Secmen.class);
        criteriaQuery.groupBy(root.get("ad")); // select ad,count(ad) from group by ad
        criteriaQuery.multiselect(root.get("ad"),criteriaBuilder.count(root));

        return manager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Bazen ihtiyaçlarımızı karşılamak için doğrudan SQL komutu yazmak isteyebiliriz.
     * bu gibi durumlarda Native olarak SQL yazabilirsiniz.
     * @return
     */
    public List<Object[]> findAllNativeSQL(){
        List<Object[]> result = manager.createNativeQuery("select * from tbl_secmen").getResultList();
        return result;
    }

    public List<Secmen> findAllNativeSQLSecmen(){
        return manager.createNativeQuery("select * from tbl_secmen",Secmen.class).getResultList();
    }

    public List<Secmen> findAllNamedQuery(){
        return manager.createNamedQuery("Secmen.findAll",Secmen.class).getResultList();
    }

    public Optional<Secmen> findOptionalById(Long id){
        Secmen secmen;
        TypedQuery<Secmen> query = manager.createNamedQuery("Secmen.findById",Secmen.class);
        query.setParameter("id",id);
        try {
            secmen=query.getSingleResult();
            return Optional.of(secmen);
        }catch (NoResultException e){
            return Optional.empty();
        }
    }

}
