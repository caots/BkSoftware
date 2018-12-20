package vn.hust.caots.Impl;

public interface I_Google {

    String creatGoogleAuthorizationURL();

    void creatGoogleAccessToken(String code);

    String getData();
}
