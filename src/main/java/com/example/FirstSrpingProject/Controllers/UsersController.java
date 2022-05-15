package com.example.FirstSrpingProject.Controllers;

import com.example.FirstSrpingProject.AllUsersDTO;
import com.example.FirstSrpingProject.Entities.UserEntity;
import com.example.FirstSrpingProject.Services.UsersServices;
import com.example.FirstSrpingProject.StatusResult;
import com.example.FirstSrpingProject.UserDTO;
import com.example.FirstSrpingProject.UserPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UsersController {

    @Autowired
    private UsersServices usersServices;




    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<String> getUsers(
        @QueryParam("pageSize") Integer pageSize,
        @QueryParam("pageNumber") Integer pageNumber
        ) throws JsonProcessingException {

       HttpHeaders resHeaders = new HttpHeaders();
       resHeaders.set("content-type", "application/json");

       if (!usersServices.validatePaginationParams(pageSize,pageNumber)){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Params");
       }

       AllUsersDTO allUsersDTO = new AllUsersDTO();

       //List<UserEntity>

       return ResponseEntity
               .ok()
               .headers(resHeaders)
               .body(new ObjectMapper().writeValueAsString(usersServices.getAllUsers(pageSize,pageNumber).values().toArray()));
    }

    @PostMapping("/users/create")
    @ResponseBody
    public ResponseEntity<String> addUser(@RequestBody UserDTO user) throws JsonProcessingException {

        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.set("content-type", "application/json");

        return ResponseEntity
                .ok()
                .headers(resHeaders)
                .body(new ObjectMapper().writeValueAsString(usersServices.addUser(user.name, user.mail)));
    }

    @GetMapping("/users/{id}")
    @ResponseBody
    public ResponseEntity<String> showUser(@PathVariable int id) throws JsonProcessingException {

        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.set("content-type", "application/json");

        UserEntity user = usersServices.showUser(id);
        if (user==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return ResponseEntity
                .ok()
                .headers(resHeaders)
                .body(new ObjectMapper().writeValueAsString(usersServices.showUser(id)));
    }

    @PutMapping("/users/{id}/update")
    @ResponseBody
    public ResponseEntity<String> editUser(@RequestBody UserDTO user, @PathVariable int id) throws JsonProcessingException {

        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.set("content-type", "application/json");

        return ResponseEntity
                .ok()
                .headers(resHeaders)
                .body(new ObjectMapper().writeValueAsString(usersServices.editUser(id,user.name, user.mail)));
    }

    @DeleteMapping("/users/{id}/remove")
    @ResponseBody
    public ResponseEntity<String> deleteUser(@PathVariable int id) throws JsonProcessingException {

        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.set("content-type", "application/json");
        usersServices.removeUser(id);

        return ResponseEntity
                .ok()
                .headers(resHeaders)
                .body(new ObjectMapper().writeValueAsString(new StatusResult(true)));
    }



/*

    @GetMapping("/{id}/get")
    @ResponseBody
    public ResponseEntity<String> getUsers(
            @PathVariable Long id
    ){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("content-type", "application/json");
        try{
            UserEntity user = users.stream().filter(users -> id.equals(users.getId())).findAny().orElse(null);
            if (user != null) {
                return ResponseEntity.ok().headers(responseHeaders).body(new ObjectMapper().writeValueAsString(user));
            }
            return ResponseEntity.status(404).body("404 error");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(500).body("500 error");
    }

    @GetMapping("/{id}/remove")
    @ResponseBody
    public ResponseEntity<String> removeUsers(
            @PathVariable Long id
    ){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("content-type", "application/json");
        try{
            UserEntity user = users.stream().filter(users -> id.equals(users.getId())).findAny().orElse(null);
            if (user != null) {
                users.removeIf(r -> r.getId() == id);
                return ResponseEntity.ok().headers(responseHeaders).body(new ObjectMapper().writeValueAsString("user removed"));
            }
            return ResponseEntity.status(404).body("404 error");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(500).body("500 error");
    }

    @GetMapping("/add")
    @ResponseBody
    public Object addOneUser(
        @RequestParam Integer id,
        @RequestParam String name
    ){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("content-type", "application/json");
        try{
            users.add(new UserEntity(id,name));
            return ResponseEntity.ok().headers(responseHeaders).body(new ObjectMapper().writeValueAsString("user with id=" +id+ " and with name="+name+ " added"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(500).body("500 error");
    } */




}
