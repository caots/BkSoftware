package vn.hust.caots.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.hust.caots.Impl.I_Menu;
import vn.hust.caots.entities.Menu;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MenuDao implements Serializable, I_Menu {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(MenuDao.class.getName());

    @Autowired
    SessionFactory sessionFactory;

    public List<Menu> getAllMenu() {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<Menu> menus = session.createQuery("from Menu").getResultList();
            return menus;
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "get-product exeption: {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public void saveMenu(Menu menu) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(menu);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "creat menu exception: {0}", ex.getMessage());
        }
    }

    @Override
    public void updateMenu(Menu menu) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(menu);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "update menu exception: {0}", ex.getMessage());
        }
    }

    @Override
    public void deleteMenu(Menu menu) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = " update from Menu set status = 0 where id = :id ";
            Query query = session.createQuery(hql);
            query.setParameter("id", menu.getId()).executeUpdate();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "delete menu exception: {0}", ex.getMessage());
        }
    }

    @Override
    public int getMaxId() {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "select max(id) from Menu";
            Query query = session.createQuery(hql);
            int maxId = (int) query.getResultList().get(0) + 1;
            return maxId;
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "get max id menu exception: {0}", ex.getMessage());
        }
        return 0;
    }

    @Override
    public Menu getMenu(int id) {
        try {
            Session session= sessionFactory.getCurrentSession();
            return  session.get(Menu.class,id);
        }catch(Exception ex){
            LOG.log(Level.SEVERE, "get menu exception: {0}", ex.getMessage());
        }
        return null;
    }


}
