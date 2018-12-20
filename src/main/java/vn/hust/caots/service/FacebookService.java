package vn.hust.caots.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hust.caots.dao.FacebookDao;
import vn.hust.caots.entities.User;


@Service
public class FacebookService {

    @Autowired
    FacebookDao facebookDao;

    public String createFacebookAuthorizationURL() {
        return facebookDao.creatFacebookAuthorizationURL();
    }

    public void creatFacebookAccessToken(String code) {
        facebookDao.creatFacebookAccessToken(code);
    }

    public User getData() {
        return facebookDao.getData();
    }
}