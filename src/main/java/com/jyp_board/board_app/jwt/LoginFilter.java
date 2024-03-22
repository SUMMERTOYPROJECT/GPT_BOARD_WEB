package com.jyp_board.board_app.jwt;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    Logger logger  = LoggerFactory.getLogger(LoginFilter.class);

    private final AuthenticationManager authenticationManager;
    //JWTUtil 주입
    private final JWTUtil jwtUtil;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {

        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        //클라이언트 요청에서 username, password 추출
        // HttpServletRequest의 InputStream으로부터 JSON 문자열을 읽음
        try {
            // HttpServletRequest에서 JSON 데이터를 읽음
            BufferedReader reader = request.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            // ObjectMapper를 사용하여 JSON 데이터를 Java 객체로 변환
            ObjectMapper mapper = new ObjectMapper();
            LoginRequest loginRequest = mapper.readValue(sb.toString(), LoginRequest.class);

            // LoginRequest 객체에서 username과 password를 추출
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();

            //스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 함
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

            //token에 담은 검증을 위한 AuthenticationManager로 전달
            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            throw new AuthenticationException("JSON 파싱 실패", e) {};
        }
    }

    //로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {

        //UserDetailsS
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String username = customUserDetails.getUsername();
        String nickname = customUserDetails.getNickname();
        String email = customUserDetails.getEmail();
        String memo = customUserDetails.getMemo();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();

//        String token = jwtUtil.createJwt(username, nickname, email, memo,  role,  60*60*100*10L);

        String token = jwtUtil.createJwt(username, nickname,  60*60*100*10L);
        response.addHeader("Authorization", "Bearer " + token);
        System.out.println("로그인 성공");

    }

    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        //로그인 실패시 401 응답 코드 반환
        response.setStatus(401);
    }

    private static class LoginRequest {
        private String username;
        private String password;

        // getter와 setter는 Lombok의 @Data 어노테이션을 사용하여 자동 생성 가능
        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}