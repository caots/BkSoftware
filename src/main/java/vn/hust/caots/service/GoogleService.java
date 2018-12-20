package vn.hust.caots.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hust.caots.dao.GoogleDao;


@Service
public class GoogleService {

    @Autowired
    GoogleDao googleDao;

    public String creatGoogleAuthorizationURL() {
        return googleDao.creatGoogleAuthorizationURL();
    }

    public void creatGoogleAccessToken(String code) {
        googleDao.creatGoogleAccessToken(code);
    }

    public String getData() {
        return googleDao.getData();
    }
}


