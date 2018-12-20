package vn.hust.caots.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Repository;
import vn.hust.caots.Impl.I_Facebook;
import vn.hust.caots.service.GoogleService;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class FacebookDao implements I_Facebook, Serializable {

    @Autowired
    UserDao userDao;

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(GoogleService.class.getName());

    private final String facebookAppId = "1962500190511684";

    private final String facebookSecret = "3480a0d460c9cf93f1cbc5c89248ee19";

    private final String RedirectUrl = "https://localhost:8443/BkSoftware_war/connect-facebook";

    private String accessToken;

    @Override
    public String creatFacebookAuthorizationURL() {
        try {
            FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId, facebookSecret);
            OAuth2Operations oAuth2Operations = connectionFactory.getOAuthOperations();
            OAuth2Parameters oAuth2Parameters = new OAuth2Parameters();
            oAuth2Parameters.setRedirectUri(RedirectUrl);
            oAuth2Parameters.setScope("public_profile,email");
            return oAuth2Operations.buildAuthenticateUrl(oAuth2Parameters);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "creat url exeption: {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public void creatFacebookAccessToken(String code) {
        try {
            FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId, facebookSecret);
            AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(code, RedirectUrl, null);
            accessToken = accessGrant.getAccessToken();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "creat Token exeption: {0}", ex.getMessage());
        }
    }

    @Override
    public vn.hust.caots.entities.User getData() {
        try {
            Facebook facebook = new FacebookTemplate(accessToken);
            User user = facebook.fetchObject("me", User.class);
            vn.hust.caots.entities.User user1 = new vn.hust.caots.entities.User();
            user1.setName(user.getName());
            user1.setEmail(user.getEmail());
            user1.setFacebookid(user.getId());
            user1.setFacebooklink(user.getLink());
            return user1;
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "get data exeption: {0}", ex.getMessage());
        }
        return null;
    }


}
