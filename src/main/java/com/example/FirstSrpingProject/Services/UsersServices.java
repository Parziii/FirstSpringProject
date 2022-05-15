package com.example.FirstSrpingProject.Services;

import com.example.FirstSrpingProject.Entities.UserEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class UsersServices {
    private Map<Integer, UserEntity> users = new HashMap<>();

    public Map<Integer, UserEntity> getAllUsers(Integer pageSize, Integer pageNumber){

        if (pageNumber == null)
            pageNumber = 1;

        if (pageSize == null)
            pageSize = users.size();

        Integer finalPageSize = pageSize;
        Integer finalPageNumber = pageNumber;

        int toIndex = finalPageSize * finalPageNumber;
        int fromIndex = (finalPageSize * finalPageNumber) - finalPageSize;

        return users.entrySet().stream().filter(map -> map.getKey() <= toIndex && map.getKey() > fromIndex)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public boolean validatePaginationParams(Integer pageSize, Integer pageNumber) {
        if (pageNumber == null)
            pageNumber = 1;

        if (pageSize == null)
            pageSize = users.size();

        return pageSize >= 1 && pageSize <= 100 && pageNumber >= 1;
    }

    public UserEntity addUser(String name, String mail){
        users.put(users.size()+1,new UserEntity(users.size()+1,name,mail));
        return users.get(users.size());
    }

    public UserEntity showUser(int id){
        return users.get(id);
    }

    public UserEntity editUser(int id, String name, String mail){
        users.get(id).name = name;
        users.get(id).mail = mail;

        return users.get(id);
    }

    public void removeUser(int id){
        users.remove(id);
    }

    @PostConstruct
    private void onCreate() {
        try (Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "/Users.txt"))) {
            int counter = 1;
            while (scanner.hasNext()) {
                var x = scanner.nextLine().split(",");
                users.put(counter, new UserEntity(counter, x[0], x[1]));
                counter++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    private void onDestroy() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/Users.txt"))) {
            users.forEach((key, value) -> {
                try {
                    writer.write(value.name + "," + value.mail + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
