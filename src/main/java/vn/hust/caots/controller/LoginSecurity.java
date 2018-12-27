package vn.hust.caots.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class LoginSecurity {
    @GetMapping("login")
    public String errorPage(@RequestParam(required = false) String message, ModelMap mm) {
        if (message != null && !message.isEmpty()) {

            if (message.equals("error")) {
                mm.put("message", "login fail !");
            }
            if (message.equals("session-time-out")) {
                mm.put("message", "Time out session !");
            }
            if (message.equals("max-session")) {
                mm.put("message", "This account has been login from another device !");
            }

            if(message.equals("access-denied")){
                mm.put("message","access denied");
            }
        }
        return "login";
    }

    @RequestMapping("admin-security")
    public String admin() {
        return "admin";
    }

    @RequestMapping("logout")
    public String logout(final ModelMap mm) {
        mm.put("message", "Logged out!");
        return "login";
    }

    @GetMapping("user-security")
    public String user() {
        return "user";
    }
}
