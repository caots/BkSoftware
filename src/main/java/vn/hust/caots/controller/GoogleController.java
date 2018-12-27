package vn.hust.caots.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.hust.caots.service.GoogleService;


@RestController
@RequestMapping("/")
public class GoogleController {
    @Autowired
    GoogleService googleService;


    @RequestMapping("/create-google-authorization")
    public String creatGoogleAuthorization() {
        return googleService.creatGoogleAuthorizationURL();
    }

    @GetMapping("/connect-google")
    public void creatGoogleAccessToken(@RequestParam("code") String code) {
        googleService.creatGoogleAccessToken(code);
    }

    @GetMapping(value = "/get-data-google", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getData() {
        String profile= googleService.getData();
        if (profile != null && !profile.isEmpty()) {
            return new ResponseEntity<>(profile, HttpStatus.OK);
        }
        return new ResponseEntity<>("Empty Data", HttpStatus.NO_CONTENT);
    }

}
