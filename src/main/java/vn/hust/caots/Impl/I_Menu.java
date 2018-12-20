package vn.hust.caots.Impl;

import vn.hust.caots.entities.Menu;

import java.util.List;

public interface I_Menu {

    List<Menu> getAllMenu();

    void saveMenu(Menu menu);

    void updateMenu(Menu menu);

    void deleteMenu(Menu menu);

    int getMaxId();

    Menu getMenu(int id);

}
