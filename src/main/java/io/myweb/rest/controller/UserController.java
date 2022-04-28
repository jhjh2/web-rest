package io.myweb.rest.controller;

import io.myweb.rest.entity.User;
import io.myweb.rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Rest 기반의 클래스
// 요청과 응답을 JSON 형태로 한다
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users") // post방식이기 때문에 정보가 body에 담겨서 온다
    public String register(@RequestBody User newUser){
        return userService.register(newUser);
    }

    //@RequestMapping(value = "/users", method = RequestMethod.GET)
    @GetMapping("/users/{id}") // path variable
    public User find(@PathVariable String id){
        return userService.find(id);
    }

    @GetMapping("/users")
    public List<User> findAll(){
        return userService.findAll();
    }

    @PutMapping("/users/{id}")
    public void modify(@PathVariable String id, @RequestBody User newUser){
        userService.modify(id, newUser);
    }

    @DeleteMapping("/users/{id}")
    public void remove(@PathVariable String id){
        userService.remove(id);
    }
}
