package com.jyp_board.board_app.service;

import com.jyp_board.board_app.domain.UserAccount;
import com.jyp_board.board_app.dto.UserAccountDto;
import com.jyp_board.board_app.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private Logger logger = LoggerFactory.getLogger(UserAccountService.class);

    public ResponseEntity<String> joinUser(UserAccountDto accountDto){
        boolean isUser = userAccountRepository.existsByUserId(accountDto.userId());
        if(isUser){
            return ResponseEntity.badRequest().body("이미 존재하는 아이디입니다.");
        }
        // 비밀번호 인코딩
        UserAccount user = accountDto.toEntity();
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        userAccountRepository.save(user);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }
}
