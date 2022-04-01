package com.example.FirstSrpingProject.Entities;

public class UserEntity {
    private long Id;
    private String name;

    public UserEntity(long id, String name) {
        Id = id;
        this.name = name;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
