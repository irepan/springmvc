package com.alcatrazstudios.springmvc.domain.security;

import com.alcatrazstudios.springmvc.domain.AbstractDomainClass;
import com.alcatrazstudios.springmvc.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role extends AbstractDomainClass{
    private String role;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="user_role")
    private List<User> users = new ArrayList<User>();

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        if (!this.users.contains(user)){
            this.users.add(user);
        }
        if (!user.getRoles().contains(this)){
            user.getRoles().add(this);
        }
    }

    public void removeUser(User user){
//        if (this.users.contains(user)){
            this.users.remove(user);
//        }
//        if (user.getRoles().contains(this)){
            user.getRoles().remove(this);
//        }
    }
}
