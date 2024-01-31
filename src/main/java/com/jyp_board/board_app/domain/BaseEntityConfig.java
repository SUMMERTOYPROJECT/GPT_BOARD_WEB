package com.jyp_board.board_app.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)

public class BaseEntityConfig {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate @Column(nullable = false, updatable = false)
    private LocalDateTime createAt; // 생성 일시

    @CreatedBy @Column(nullable = false, length = 100, updatable = false)
    private String createBy;  //생성자

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate @Column(nullable = false)
    private LocalDateTime modifiedAt; //수정 일시

    @LastModifiedBy @Column(nullable = false, length = 100)
    private String modifiedBy; //수정자
}