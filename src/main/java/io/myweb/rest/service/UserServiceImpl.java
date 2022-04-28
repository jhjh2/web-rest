package io.myweb.rest.service;

import io.myweb.rest.entity.User;
import io.myweb.rest.store.UserStore;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 스프링 빈으로 등록
// 빈 객체 주입
// 3. 롬복이 제공하는 어노테이션을 통한 빈 객체 주입
// 2번 방법의 생성자가 자동으로 추가되는 효과
//@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    // 1. 어노테이션을 통한 빈 객체 주입
    //@Autowired
    private final UserStore userStore; // final -> 반드시 초기화가 이루어져아 하고, 초기화 이후 변경 불가능

    // 2. 생성자를 통한 빈 객체 주입
   public UserServiceImpl(UserStore userStore) {
        this.userStore = userStore;
    }

    @Override
    public String register(User newUser) {
        return this.userStore.create(newUser);
    }

    @Override
    public void modify(String id, User newUser) {
        this.userStore.update(newUser);
    }

    @Override
    public void remove(String id) {
        this.userStore.delete(id);
    }

    @Override
    public User find(String id) {
        return this.userStore.retrieve(id);
    }

    @Override
    public List<User> findAll() {
        return this.userStore.retrieveAll();
    }
}
