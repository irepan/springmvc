package com.alcatrazstudios.springmvc.services.reposervices;

import com.alcatrazstudios.springmvc.domain.User;
import com.alcatrazstudios.springmvc.repositories.UserRepository;
import com.alcatrazstudios.springmvc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("springdatajpa")
public class UserServiceRepoImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> listAll() {
        List<User> users = new ArrayList<>();
        this.userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.isPresent()?user.get():null;
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
