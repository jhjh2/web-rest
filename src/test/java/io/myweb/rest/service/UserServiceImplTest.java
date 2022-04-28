package io.myweb.rest.service;

import io.myweb.rest.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired // 단위 테스트에서는 생성자를 통한 주입, 롬복이 제공하는 어노테이션을 통한 빈 객체 주입이 불가능하다
    private UserService userService;

    private User kim;
    private User lee;

    @BeforeEach // 단위 테스트가 수행될 때마다 호출된다
    public void startUp() {
        this.kim =
        this.lee = new User("lee","lee@mail.com");
        this.userService.register(kim);
        this.userService.register(lee);
    }

    // junit을 통한 테스트
    // 화이트박스 테스트: 테스트의 결과를 알고 검증함
    @Test
    public void registerTest() {

        User sample = User.sample();
        assertThat(this.userService.register(sample)).isEqualTo(sample.getId());
        assertThat(this.userService.findAll().size()).isEqualTo(3);

        this.userService.remove(sample.getId());
    }

    @Test
    public void find() {

        assertThat(this.userService.find(lee.getId())).isNotNull();
        assertThat(this.userService.find(lee.getId()).getEmail()).isEqualTo(lee.getEmail());

    }

    @AfterEach // 단위 테스트가 끝날 때마다 호출된다
    public void cleanUp() {
        this.userService.remove(kim.getId());
        this.userService.remove(lee.getId());

    }
}
