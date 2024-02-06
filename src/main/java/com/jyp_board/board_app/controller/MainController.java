package com.jyp_board.board_app.controller;

import com.jyp_board.board_app.dto.UserAccountDto;
import com.jyp_board.board_app.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final UserAccountService userAccountService;

    @PostMapping("/signup")
    public String join(@RequestBody UserAccountDto userAccountDto){
        return userAccountService.joinUser(userAccountDto);
    }

}
