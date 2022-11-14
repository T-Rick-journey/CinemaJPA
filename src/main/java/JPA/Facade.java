package main.java.JPA;

import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class Facade<T> {
    private final EntityManager em;
    private final Class<T> entityClass;
    
    public Facade(Class<T> entityClass) {
        this.entityClass = entityClass;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Cinema");
        EntityManager entityManager = factory.createEntityManager();
        em = entityManager;
    }
    
    public T findById(int id) {
        return getEntityManager().find(entityClass, id);
    }
    
    public void insert(T t) {
        EntityTransaction inserir = em.getTransaction();
        inserir.begin();
        getEntityManager().persist(t);
        inserir.commit();
    }

    public void update(T t) {
        EntityTransaction atualizacao = em.getTransaction();
        atualizacao.begin();
        getEntityManager().merge(t);
        atualizacao.commit();
    }

    public void delete(T t) {
        EntityTransaction erase = em.getTransaction();
        erase.begin();
        getEntityManager().remove(getEntityManager().merge(t));
        erase.commit();
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public EntityManager getEntityManager() {
	return em;
    }
}
