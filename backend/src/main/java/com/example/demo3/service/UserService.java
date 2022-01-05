package com.example.demo3.service;
import com.example.demo3.Exceptions.UserNotFoundException;
import com.example.demo3.entity.User;
import com.example.demo3.repisotory.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {

    private final Userrepo userrepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public UserService(Userrepo userrepo,BCryptPasswordEncoder passwordEncoder) {
        this.userrepo = userrepo;
        this.passwordEncoder= passwordEncoder;
    }
    public User addUser (User user){
        user.setMotdepasse(passwordEncoder.encode(user.getMotdepasse() ));
        return userrepo.save(user);
    }

    public  List<User> findAllUser(){
        return userrepo.findAll();
    }

    public User updateUser(User user){
        return userrepo.save(user);
    }

    public User findUserById(Long id){
        return  userrepo.findUserById(id).orElseThrow(() -> new UserNotFoundException("user by id" + id + "cannot be found"));
    }

    public void deleteUser(Long id ){
        userrepo.deleteUserById(id);
    }

    public User findUserByEmail(String mail) {
        return userrepo.findUserByEmail(mail);
    }
}





