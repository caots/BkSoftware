package vn.hust.caots.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hust.caots.entities.Menu;
import vn.hust.caots.service.MenuService;

import java.util.List;


@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Menu>> getAllMenu(){
        List<Menu> menus= menuService.getAllMenu();
        if(menus == null&& menus.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(menus,HttpStatus.OK);
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveMenu(@RequestParam Menu menu){
        List<Menu> menus= menuService.getAllMenu();
        int index=1;
        for(Menu menu1: menus){
            if(menu.getId()== menu1.getId()){
                index=0;
            }
        }
        if(index==0){
            return  new ResponseEntity<>("Menu Already Exit !",HttpStatus.CONFLICT);
        }

        menuService.saveMenu(menu);
        return  new ResponseEntity<>("creat success",HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMenuById(@PathVariable("id") int id){
        Menu menu= menuService.getMenu(id);
        if(menu.getStatus()==1){
            return  new ResponseEntity<>(menu,HttpStatus.OK);
        }else{
            return  new ResponseEntity<>("not found Menu",HttpStatus.NOT_FOUND);
        }
    }


}
