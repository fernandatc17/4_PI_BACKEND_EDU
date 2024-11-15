package com.tecsup.EduconnectBackEnd.services;

import com.tecsup.EduconnectBackEnd.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User save(User user);
    void delete(Long id);
}
