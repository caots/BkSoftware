package vn.hust.caots.Impl;

public interface I_Linkedin {

    String creatLinkedinAuthorizationURL();

    void creatLinkedinAccessToken(String code);

    String getData();

}
