package com.example.FirstSrpingProject.Controllers;

import com.example.FirstSrpingProject.Entities.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersController {
    List<UserEntity> users = new ArrayList<>();


   /* @RequestMapping("/users")
    @ResponseBody
    public Object getUsers(
        @RequestParam Integer pageNumber,
        @RequestParam Integer pageSize
        ) {
        return pageNumber + "  " + pageSize;
    }*/

    @RequestMapping("/users")
    @ResponseBody
    public Object addUsers() {
        users.add(new UserEntity(11,"John"));
        users.add(new UserEntity(23,"Eugeniusz"));
        users.add(new UserEntity(73,"Marek"));

        return users;
    }
    @RequestMapping("/users/{id}/get")
    @ResponseBody
    public Object getUsers(
            @RequestParam Long id
    ){
        if (users.contains(id))
        {
            return users.get(id.intValue());
        }
        return id;
    }
    /*
    @RequestMapping("/users/{id}/get")
    @ResponseBody
    public Object getUsers(
            @RequestParam Long id
    ){
        return new UserEntity(id, "John" + id);
    }*/




}
