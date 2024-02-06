package com.jyp_board.board_app.jwt;

import com.jyp_board.board_app.domain.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final UserAccount userAccount;

    public CustomUserDetails(UserAccount userAccount) {

        this.userAccount = userAccount;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

//                return UserAccount.getRole();
                return "ROLE_USER";
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {

        return userAccount.getUserPassword();
    }

    @Override
    public String getUsername() {

        return userAccount.getUserId();
    }

    public String getNickname(){
        return userAccount.getNickname();
    }

    public String getEmail(){
        return userAccount.getEmail();
    }

    public String getMemo(){
        return userAccount.getMemo();
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}