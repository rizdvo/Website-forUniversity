package core.repositories;

import core.entities.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepository {

    public SessionFactory sessionFactory;

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<User> findAll() {
        Query<User> query = getSession().createQuery("from User", User.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<User> findAll(int offset, int limit) {
        Query<User> query = getSession().createQuery("from User", User.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public int getAllCount() {
        Query<Integer> query = getSession().createNativeQuery("select count(users.id) from users", Integer.class);
        return query.getSingleResult();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public User findBy(int id) {
        Query<User> query = getSession().createQuery("from User where id = :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<User> findBy(String username, String password) {
        try {
            Query<User> query = getSession().createQuery("from User where username = :username and password = :password", User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<User> findBy(String username) {
        try {
            Query<User> query = getSession().createQuery("from User where username = :username", User.class);
            query.setParameter("username", username);
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(User user) {
        getSession().saveOrUpdate(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(User user) {
        getSession().remove(user);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
