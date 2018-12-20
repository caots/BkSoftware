package vn.hust.caots.dao;

import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.social.linkedin.api.impl.LinkedInTemplate;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Repository;
import vn.hust.caots.Impl.I_Linkedin;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class LinkedinDao implements I_Linkedin, Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(LinkedinDao.class.getName());

    private final String linkedinAppId = "785ud89mxx3sds";

    private final String linkedinSecret = "4q4UkwU2mMWSdqvX";

    private final String RedirectUrl = "https://localhost:8443/BkSoftware_war/connect-linkedin";

    private String accessToken;

    @Override
    public String creatLinkedinAuthorizationURL() {
        try {
            LinkedInConnectionFactory linkedInConnectionFactory = new LinkedInConnectionFactory(linkedinAppId, linkedinSecret);
            OAuth2Operations oAuth2Operations = linkedInConnectionFactory.getOAuthOperations();
            OAuth2Parameters oAuth2Parameters = new OAuth2Parameters();
            oAuth2Parameters.setRedirectUri(RedirectUrl);
            oAuth2Parameters.setScope("r_fullprofile%20r_emailaddress%20w_share");
            oAuth2Parameters.setState("DCEeFWf45A53sdfKef424");
            return oAuth2Operations.buildAuthenticateUrl(oAuth2Parameters);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "creat url exeption: {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public void creatLinkedinAccessToken(String code) {

        try {
            LinkedInConnectionFactory linkedInConnectionFactory = new LinkedInConnectionFactory(linkedinAppId, linkedinSecret);
            AccessGrant grant = linkedInConnectionFactory.getOAuthOperations().exchangeForAccess(code, RedirectUrl, null);
            accessToken = grant.getAccessToken();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "creat Token exeption: {0}", ex.getMessage());
        }

    }

    @Override
    public String getData() {
        try {
            LinkedIn linkedIn = new LinkedInTemplate(accessToken);
            LinkedInProfileFull linkedInProfileFull = linkedIn.profileOperations().getUserProfileFull();
            String email = linkedInProfileFull.getEmailAddress();
            String name = linkedInProfileFull.getFirstName() + linkedInProfileFull.getLastName();
            String imageUrl = linkedInProfileFull.getProfilePictureUrl();
            return "Email: " + email + "____  Name:" + name + "____  ImageUrl: " + imageUrl;
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "get data exeption: {0}", ex.getMessage());
        }
        return null;
    }
}
