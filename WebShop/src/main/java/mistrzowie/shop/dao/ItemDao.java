package mistrzowie.shop.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Arek
 */
@Stateless
@LocalBean
public class ItemDao {

    @PersistenceContext
    private EntityManager em;
    
    public ItemEntity insert(ItemEntity entity) {
        em.persist(entity);
        return entity;
    }
    
    public ItemEntity update(ItemEntity entity) {
        em.merge(entity);
        return entity;
    }
    
    public ItemEntity findByName(String name) {
        return (ItemEntity) em.createQuery("select e from ItemEntity e where e.name = :name")
                .setParameter("name", name)
                .getSingleResult();
    }
    
    public void remove(ItemEntity entity) {
        em.remove(em.merge(entity));
    }
}
