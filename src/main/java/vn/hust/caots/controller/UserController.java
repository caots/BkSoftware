package vn.hust.caots.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hust.caots.entities.User;
import vn.hust.caots.service.UserService;

/**
 * @author caots
 */
@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    /* ---------------- GET USER BY ID ------------------------ */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserById(@PathVariable int id) {
        User user = userService.getUser(id);
        if (user != null & user.getStatus() == 1) {
            return new ResponseEntity<Object>(user, HttpStatus.OK);
        }
        return new ResponseEntity<Object>("not found User", HttpStatus.NO_CONTENT);
    }

    /* ---------------- CREATE NEW USER ------------------------ */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        List<User> users = userService.getListUser();
        Map<Integer, User> mapUser = new HashMap<>();
        int i = 0;
        for (User user1 : users) {
            mapUser.put(i++, user1);
        }
        if (mapUser.containsKey(user.getId())) {
            return new ResponseEntity<>("Product Already Exit !", HttpStatus.CONFLICT);
        }
        userService.saveUser(user);
        return new ResponseEntity<>("creat success!", HttpStatus.CREATED);
    }

    /* ---------------- DELETE USER ------------------------ */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        User user = userService.getUser(id);
        if (user == null || user.getStatus() == 0) {
            return new ResponseEntity<>("Not Found Product", HttpStatus.OK);
        }
        userService.deleteUser(user);
        return new ResponseEntity<>("Delete!", HttpStatus.OK);
    }

    /* ---------------- SEARCH /PAGINATION------------------------ */
    @GetMapping(value = "search/{str}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> searchUser(@PathVariable String str,
                                                 @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                                 @RequestParam(required = false, value = "total", defaultValue = "3") int total) {
        List<User> users = userService.searchUser(str, page, total);
        if (users != null) {
            for (User user : users) {
                if (user.getStatus() == 1) {
                    return new ResponseEntity<>(users, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    /* ---------------- PAGINATION ------------------------ */

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUserLimit(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                                   @RequestParam(required = false, value = "total", defaultValue = "3") int total) {
        int totalRecord = userService.getListUser().size();
        int totalPages = (int) Math.ceil(totalRecord / total);
        page = (page - 1) * total;
        List<User> users = userService.getListUserLimit(page, total);
        if (users != null) {
            for (User user : users) {
                if (user.getStatus() == 1) {
                    return new ResponseEntity<>(users, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping(value = "/login",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> checkLogin(@RequestParam String email,
                                             @RequestParam String passqord) {
        boolean check = userService.checkLogin(email, passqord);
        if (check) {
            return new ResponseEntity<>("Login success !", HttpStatus.OK);
        }
        return new ResponseEntity<>("Login fail !", HttpStatus.NOT_FOUND);
    }
}
