package vn.hust.caots.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.hust.caots.service.LinkedinService;

@RestController
@RequestMapping("/")
public class LinkedinController {

    @Autowired
    LinkedinService linkedinService;

    @GetMapping("create-linkedin-authorization")
    public String creatLinkedinAuthorization() {
        return linkedinService.creatLinkedinAuthorization();
    }

    @GetMapping("connect-linkedin")
    public void creatGoogleAccessToken(@RequestParam("code") String code) {
        linkedinService.creatGoogleAccessToken(code);
    }

    @GetMapping("get-data-linkedin")
    public String getData() {
        return linkedinService.getData();
    }

}
