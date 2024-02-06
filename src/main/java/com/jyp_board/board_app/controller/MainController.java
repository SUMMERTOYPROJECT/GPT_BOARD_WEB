package com.jyp_board.board_app.controller;

import com.jyp_board.board_app.dto.UserAccountDto;
import com.jyp_board.board_app.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class MainController {
    private final UserAccountService userAccountService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> join(@RequestBody UserAccountDto userAccountDto){
        return userAccountService.joinUser(userAccountDto);
    }
}
