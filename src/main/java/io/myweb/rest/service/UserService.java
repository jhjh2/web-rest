package io.myweb.rest.service;

import io.myweb.rest.entity.User;

import java.util.List;

public interface UserService {

    String register(User newUser);
    void modify(String id, User newUser);
    void remove(String id);

    User find(String id);
    List<User> findAll();
}
