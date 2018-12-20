package vn.hust.caots.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.hust.caots.entities.User;
import vn.hust.caots.service.FacebookService;
import vn.hust.caots.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/")
public class FacebookController {

    @Autowired
    FacebookService facebookService;

    @Autowired
    UserService userService;

    @GetMapping("/create-facebook-authorization")
    public String creatFacebookAuthorization() {
        return facebookService.createFacebookAuthorizationURL();

    }

    @GetMapping("/connect-facebook")
    public void createFacebookAccessToken(@RequestParam("code") String code) {
        facebookService.creatFacebookAccessToken(code);
    }

    @GetMapping(value = "/get-data-facebook", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getData() {
        List<User> users = userService.getListUser();
        User userFb = facebookService.getData();
        int index = 1;
        for (User user : users) {
            if (userFb.getFacebookid().equals(user.getFacebookid())) {
                index = 0;
            }
        }
        if (index == 1) {
            userService.saveUser(userFb);
        }
        if (userFb != null) {
            return new ResponseEntity<Object>(userFb, HttpStatus.OK);
        }
        return new ResponseEntity<>("Empty Data", HttpStatus.NO_CONTENT);
    }
}

