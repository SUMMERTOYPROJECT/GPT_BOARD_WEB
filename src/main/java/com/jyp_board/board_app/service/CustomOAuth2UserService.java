package com.jyp_board.board_app.service;

import com.jyp_board.board_app.domain.UserAccount;
import com.jyp_board.board_app.domain.UserEntity;
import com.jyp_board.board_app.dto.CustomOAuth2User;
import com.jyp_board.board_app.dto.UserDTO;
import com.jyp_board.board_app.dto.response.GoogleResponse;
import com.jyp_board.board_app.dto.response.NaverResponse;
import com.jyp_board.board_app.dto.response.OAuth2Response;
import com.jyp_board.board_app.repository.UserAccountRepository;
import com.jyp_board.board_app.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserEntityRepository userEntityRepository;

    private final Logger logger = LoggerFactory.getLogger(CustomOAuth2UserService.class);


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else {

            return null;
        }
        //리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디값을 만듬
        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        logger.info("UserNAme is " + username);

        boolean isUser = userEntityRepository.existsByUsername(username);
        if(!isUser){
            logger.info("There is no User");
            UserEntity userAccount = new UserEntity(
                    oAuth2Response.getEmail(),
                    oAuth2Response.getName(),
                    username,
                    "ROLE_USER"
            );
            userEntityRepository.save(userAccount);
            logger.info("Save New User");


            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(username);
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole("ROLE_USER");
            return new CustomOAuth2User(userDTO);
        }
        else{
            logger.info("There is The User");
            UserEntity existData = userEntityRepository.findByUsername(username);
            existData.setEmail(oAuth2Response.getEmail());
            existData.setNickname(oAuth2Response.getName());

            userEntityRepository.save(existData);
            logger.info("Update New User");

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(existData.getUsername());
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole(existData.getRole());

            return new CustomOAuth2User(userDTO);
        }
    }
}
