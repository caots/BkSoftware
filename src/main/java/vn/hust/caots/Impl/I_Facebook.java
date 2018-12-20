package vn.hust.caots.Impl;

import vn.hust.caots.entities.User;

public interface I_Facebook {

    String creatFacebookAuthorizationURL();

    void creatFacebookAccessToken(String code);

    User getData();

}
