package vn.hust.caots.dao;

import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Repository;
import vn.hust.caots.Impl.I_Google;
import vn.hust.caots.service.GoogleService;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class GoogleDao implements I_Google, Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(GoogleService.class.getName());

    private  final String googleAppId = "175039674732-pb3en1q6r5e58mlh50ii9lpu1p5qfbd9.apps.googleusercontent.com";

    private  final String googeSecret = "lhkPG8V79gioi5gKlcydCC-4";

    private  final String RedirectUrl="https://localhost:8443/BkSoftware_war/connect-google";

    private String accessToken;

    public String creatGoogleAuthorizationURL() {
        try {
            GoogleConnectionFactory googleConnectionFactory = new GoogleConnectionFactory(googleAppId, googeSecret);
            OAuth2Operations oAuth2Operations = googleConnectionFactory.getOAuthOperations();
            OAuth2Parameters oAuth2Parameters = new OAuth2Parameters();
            oAuth2Parameters.setRedirectUri(RedirectUrl);
            oAuth2Parameters.setScope("https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email");
            return oAuth2Operations.buildAuthenticateUrl(oAuth2Parameters);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "creat url exeption: {0}", ex.getMessage());
        }
        return null;

    }


    public void creatGoogleAccessToken(String code) {
        try {
            GoogleConnectionFactory connectionFactory = new GoogleConnectionFactory(googleAppId, googeSecret);
            AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(code, RedirectUrl, null);
            accessToken = accessGrant.getAccessToken();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "creat access token exeption: {0}", ex.getMessage());
        }
    }

    public String getData() {
        Google google = new GoogleTemplate(accessToken);
        Person googleUser = google.plusOperations().getGoogleProfile();
        String email = googleUser.getAccountEmail();
        String name = googleUser.getDisplayName();
        String imageUrl = googleUser.getImageUrl();
        return "Email: "+email + "____  Name:" + name + "____  ImageUrl: " + imageUrl;
    }

}
