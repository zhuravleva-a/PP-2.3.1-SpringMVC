package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoHibernateImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return em.createQuery("select user from User user").getResultList();
    }

    @Override
    public User getUserById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public void update(long id, User updatedUser) {
        em.find(User.class, id);
        em.merge(updatedUser);
    }

    @Override
    public void delete(long id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }
}
