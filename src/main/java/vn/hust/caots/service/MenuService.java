package vn.hust.caots.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hust.caots.dao.MenuDao;
import vn.hust.caots.entities.Menu;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuDao menuDao;

    public List<Menu> getAllMenu() {
        return menuDao.getAllMenu();
    }

    public Menu getMenu(int id) {
        return menuDao.getMenu(id);
    }

    public void saveMenu(Menu menu) {
        menuDao.saveMenu(menu);
    }

    public void updateMenu(Menu menu) {
        menuDao.updateMenu(menu);
    }

    public void deleteMenu(Menu menu) {
        menuDao.deleteMenu(menu);
    }

    public int getMaxId() {
        return menuDao.getMaxId();
    }


}
