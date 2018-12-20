package vn.hust.caots.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hust.caots.dao.LinkedinDao;

@Service
public class LinkedinService {

    @Autowired
    LinkedinDao linkedinDao;

    public String creatLinkedinAuthorization() {
        return linkedinDao.creatLinkedinAuthorizationURL();
    }

    public void creatGoogleAccessToken(String code) {
        linkedinDao.creatLinkedinAccessToken(code);
    }

    public String getData() {
        return linkedinDao.getData();
    }
}
