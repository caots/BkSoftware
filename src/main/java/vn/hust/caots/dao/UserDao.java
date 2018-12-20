package vn.hust.caots.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.hust.caots.Impl.I_User;
import vn.hust.caots.entities.Role;
import vn.hust.caots.entities.User;

import javax.persistence.Query;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author caots
 */
@Repository // có thể gọi trực tiếp autowired
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//trang thái Proxy của class đại diện cho class luôn
public class UserDao implements I_User {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(UserDao.class.getName());

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public boolean checkLogin(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from User where email = :email and password =  :password ";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("email", email);
            query.setParameter("password", password);
            User user = (User) query.getSingleResult();
            if (user != null && user.getStatus() == 1) {
                return true;
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Login exeption: {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public void saveUser( User user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            user.setRole(getRoleID());
            user.setId(getUserID());
            session.save(user);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "insert exeption: {0}", ex.getMessage());
        }
    }

    public Role getRoleID() {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(Role.class, 2);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "get-role-id exeption: {0}", ex.getMessage());

        }
        return null;

    }

    public int getUserID() {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql="select max(id) from User ";
            Query query=session.createQuery(hql);
            int maxId= (int) query.getResultList().get(0) + 1;
            return  maxId ;
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "get-role-id exeption: {0}", ex.getMessage());
        }
        return 0;
    }

    @Override
    public List<User> getListUser() {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "from User";
            List<User> users = session.createQuery(hql, User.class).getResultList();
            return users;
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "get-all-user exeption: {0}", ex.getMessage());

        }
        return null;
    }

    @Override
    public User getUser(final int id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(User.class, id);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "get-user exeption: {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public void deleteUser(final User user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = " update from User set status = 0 where id = :id";
            session.createQuery(hql).setParameter("id", user.getId()).executeUpdate();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "delete-user exeption: {0}", ex.getMessage());
        }
    }

    @Override
    public void updateUser(final User user) {
        try {
            //Session session = sessionFactory.getCurrentSession();
            //session.update(user);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "update-user exeption: {0}", ex.getMessage());
        }

    }

    @Override
    public List<User> searchUser(final String str, int page, int total) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "from User  where name like :str or email like  :str ";
            Query query = session.createQuery(hql);
            query.setParameter("str", "%" + str + "%");
            query.setFirstResult(page);
            query.setMaxResults(total);
            List<User> users = query.getResultList();
            return users;
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "search-user exeption: {0}", ex.getMessage());
        }
        return null;

    }

    @Override
    public List<User> getListUserLimit(int page, int total) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "from User";
            Query query = session.createQuery(hql);
            query.setFirstResult(page);
            query.setMaxResults(total);
            List<User> users = query.getResultList();
            return users;
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "search-user exeption: {0}", ex.getMessage());
        }
        return null;

    }


}