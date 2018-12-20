/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.hust.caots.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hust.caots.Impl.I_User;
import vn.hust.caots.dao.UserDao;
import vn.hust.caots.entities.Role;
import vn.hust.caots.entities.User;


/**
 * @author caots
 */
@Service
public class UserService implements I_User {

    @Autowired
    UserDao userDao;

    @Override
    public boolean checkLogin(String email, String password) {
        boolean check = userDao.checkLogin(email, password);
        return check;
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    public Role getRoleID() {
        return userDao.getRoleID();
    }

    @Override
    public List<User> getListUser() {
        return userDao.getListUser();
    }

    @Override
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public List<User> searchUser(String str, int page, int total) {
        return userDao.searchUser(str, page, total);
    }

    @Override
    public List<User> getListUserLimit(int page, int total) {
        return userDao.getListUserLimit(page, total);
    }

}
