package com.jyp_board.board_app.controller;

import com.jyp_board.board_app.dto.UserAccountDto;
import com.jyp_board.board_app.dto.request.ChatGPTRequest;
import com.jyp_board.board_app.dto.response.ChatGPTResponse;
import com.jyp_board.board_app.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class MainController {

    private final UserAccountService userAccountService;

    @GetMapping("/")
    public String mainAPI() {
        return "main route";
    }
    @PostMapping("/sign-up")
    public ResponseEntity<String> join(@RequestBody UserAccountDto userAccountDto){
        return userAccountService.joinUser(userAccountDto);
    }
}
