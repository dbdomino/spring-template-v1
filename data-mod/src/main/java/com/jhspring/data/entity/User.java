package com.jhspring.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(unique = true)
    private String id;
    private String username;
    private String password;
    private String email;
    private String phone;

    private String address;
    private LocalDate birthday;
    private String gender;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime lastLoginDate;

    // JWT 용 필드
    private String refreshToken;

    private boolean active;
}
