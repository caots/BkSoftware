package vn.hust.caots.Impl;

import vn.hust.caots.entities.User;

import java.util.List;

public interface I_User {

    boolean checkLogin(String email, String password);

    void saveUser(User user);

    List<User> getListUser();

    User getUser(int id);

    void deleteUser(User user);

    void updateUser(User user);

    List<User> searchUser(String str,int page, int total);

    List<User> getListUserLimit(int start, int total);
}
