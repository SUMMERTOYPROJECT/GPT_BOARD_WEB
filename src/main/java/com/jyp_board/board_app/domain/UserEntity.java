package com.jyp_board.board_app.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
@Table(
        indexes = {
                @Index(columnList = "email", unique = true),
                @Index(columnList = "createAt"),
        })
@Entity
public class UserEntity extends BaseUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //    @Setter @Column(nullable = false) private String userPassword;
    @Setter @Column(length = 100) private String email;
    @Setter @Column(length = 100) private String nickname;
    @Setter @Column(length = 100) private String username;
    @Setter @Column(length = 100) private String role;

    public UserEntity(String email, String nickname, String username, String role) {
        this.email = email;
        this.nickname = nickname;
        this.username = username;
        this.role= role;
    }
    public static UserEntity of(String email, String nickname, String username, String role){
        return new UserEntity(email, nickname, username, role);
    }

}
