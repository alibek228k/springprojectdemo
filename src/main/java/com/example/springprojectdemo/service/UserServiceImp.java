package com.example.springprojectdemo.service;

import com.example.springprojectdemo.model.Role;
import com.example.springprojectdemo.model.User;
import com.example.springprojectdemo.repository.RoleRepository;
import com.example.springprojectdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
@Service
public class UserServiceImp {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id){
        return  userRepository.getOne(id);
    }
    public List<User> findAll(){
        return  userRepository.findAll();
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public void saveUsersInDb(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus("VERIFIED");
        Role userRole = roleRepository.findRoleByRole("SITE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
    public boolean isUserAlreadyPresent(User user){
        return false;
    }
}
