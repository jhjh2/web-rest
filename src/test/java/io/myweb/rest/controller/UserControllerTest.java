package io.myweb.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.myweb.rest.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(UserController.class) // 웹 환경에 대한 테스트를 경량화하여 진행할 수 있는 어노테이션
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc; // 웹 환경에 대한 테스트를 진행하기 위해 필요한 객체

    @Autowired
    private ObjectMapper objectMapper; // 실제로 JSON 데이터를 만들 수 있다

    private User kim;

    @BeforeEach
    void setUp() throws Exception {
        this.kim = new User("kim","kim@mail.com");
        String content = objectMapper.writeValueAsString(kim);
        mockMvc.perform(post("/users")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void register() throws Exception { // 통신 과정에서 오류가 발생할 수 있기 때문에 더 넓은 범위의 Exception 사용
        User sample = User.sample();
        String content = objectMapper.writeValueAsString(sample); // JsonProcessingException 예외처리 필요
        mockMvc.perform(post("/users")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(sample.getId()))
                .andDo(print());
    }

    @Test
    void find() throws Exception {
        mockMvc.perform(get("/users/"+kim.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(kim)))
                .andDo(print());
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void modify() throws Exception {
        User sample = User.sample();
        String content = objectMapper.writeValueAsString(sample);
        mockMvc.perform(put("/users/"+kim.getId())
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        mockMvc.perform(delete("/users/"+sample.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void remove() throws Exception {
        mockMvc.perform(delete("/users/"+kim.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @AfterEach
    void tearDown() throws Exception {
        mockMvc.perform(delete("/users/"+kim.getId()));
    }
}