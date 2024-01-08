package com.bookrealm.service;

import com.bookrealm.exception.AppException;
import com.bookrealm.model.Address;
import com.bookrealm.model.SuType;
import com.bookrealm.model.User;
import com.bookrealm.model.dto.JoinDto;
import com.bookrealm.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.bookrealm.model.SuType.NORMAL;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    public void 회원가입() {
        //given

        String email = "a@naver.com";
        String username = "이영진";
        String passwd ="1234";
        Address address = new Address("서울시", "갈현로4길", "25-9");
        String phone = "01042991435";
        SuType suType = NORMAL;

        JoinDto user = new JoinDto(email, username, passwd, address, phone, suType);

        //when
        Long saveId = userService.join(user);

        //then
        User findUser = userRepository.findById(saveId).get();
        assertEquals(user.getEmail(), findUser.getEmail());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        String email = "a@naver.com";
        String username = "이영진";
        String passwd ="1234";
        Address address = new Address("서울시", "갈현로4길", "25-9");
        String phone = "01042991435";
        SuType suType = NORMAL;

        JoinDto user = new JoinDto(email, username, passwd, address, phone, suType);
        JoinDto user2 = new JoinDto(email, username, passwd, address, phone, suType);

        //when
        userService.join(user);
        assertThrows(AppException.class, () -> userService.join(user2));
    }
}