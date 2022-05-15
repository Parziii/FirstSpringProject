package com.example.FirstSrpingProject;

import com.example.FirstSrpingProject.Entities.UserEntity;

import java.util.Collection;

public class UserPage {
    private int pageNumber;
    private int pageSize;
    private int pageCount;
    private int totalCount;
    private UserEntity users;

    public UserPage(int pageNumber, int pageSize, int pageCount, int totalCount) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.totalCount = totalCount;
    }

    public void setUsers(Collection<UserEntity> users) {
        this.users = (UserEntity) users;
    }

    public Collection<UserEntity> getUsers() {
        return (Collection<UserEntity>) users;
    }
}
