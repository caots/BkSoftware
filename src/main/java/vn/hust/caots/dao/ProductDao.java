package vn.hust.caots.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.hust.caots.Impl.I_Product;
import vn.hust.caots.entities.Product;

import javax.persistence.Query;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Repository // có thể gọi trực tiếp autowired
@Transactional(rollbackFor = Exception.class)
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//trang thái Proxy của class đại diện cho class luôn
public class ProductDao implements I_Product {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(ProductDao.class.getName());
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Product getProduct(final int id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(Product.class, id);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "get-product exeption: {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> getProductLimit(int page, int total) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "from Product";
            Query query = session.createQuery(hql);
            query.setFirstResult(page);
            query.setMaxResults(total);
            List<Product> products = query.getResultList();
            return products;
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "get-product-limit exeption: {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<Product> products = session.createQuery("from Product").getResultList();
            return products;
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "get-all-product exeption: {0}", ex.getMessage());
        }
        return null;

    }


    @Override
    public void deleteProduct(final Product product) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "update from Product set status = 0  where id = :id";
            session.createQuery(hql).setParameter("id", product.getId()).executeUpdate();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "delete-product exeption: {0}", ex.getMessage());
        }
    }

    @Override
    public void updateProduct(final Product product) {
        try {
            // Session session = sessionFactory.getCurrentSession();
            //session.update(product);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "update-product exeption: {0}", ex.getMessage());
        }

    }

    @Override
    public void saveProduct(final Product product) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(product);
            session.flush();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "insert-product exeption: {0}", ex.getMessage());
        }


    }

    @Override
    public List<Product> searchProduct(final String str, int page, int total) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "from Product pd where pd.name like :str  ";
            Query query = session.createQuery(hql);
            query.setParameter("str", "%" + str + "%");
            query.setFirstResult(page);
            query.setMaxResults(total);
            List<Product> products = query.getResultList();
            return products;
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "search-product exeption: {0}", ex.getMessage());
        }
        return null;
    }
}
