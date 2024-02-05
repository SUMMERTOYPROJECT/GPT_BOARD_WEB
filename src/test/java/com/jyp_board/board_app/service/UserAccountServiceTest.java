package com.jyp_board.board_app.service;

import com.jyp_board.board_app.config.SecurityConfig;
import com.jyp_board.board_app.domain.UserAccount;
import com.jyp_board.board_app.dto.UserAccountDto;
import com.jyp_board.board_app.repository.UserAccountRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 -로그인-")
@ExtendWith(MockitoExtension.class)
class UserAccountServiceTest {
    @InjectMocks
    private UserAccountService sut; //system under test
    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;


    @DisplayName("회원정보를 받아 회원가입을 시도한다.")
    @Test
    void givenJoinData_whenCreatingUser_thenUser(){
        // given
        UserAccountDto userAccountDto = UserAccountDto.of(
                "user3Id","mypassword","my3email@gmail.com",
                "myNickname","memo"
        );
        given(userAccountRepository.save(any(UserAccount.class))).willReturn(null);
        //when
        sut.joinUser(userAccountDto);
        //then
        then(userAccountRepository).should().save(any(UserAccount.class));
    }

    @Disabled("구현중")
    @DisplayName("아이디와 비밀번호를 통해 로그인을 시도한다.")
    @Test
    void givenIdAndPw_whenSearchingUserAccount_thenReturnUserInfo(){
        //given
        Long userId = 1l;
        String password = "password";
        given(userAccountRepository.findById(userId)).willReturn(null);
        //when



        //then
    }
}