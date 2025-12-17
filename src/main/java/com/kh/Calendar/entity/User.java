package com.kh.Calendar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_NO")
    private Long userNo;

    @Column(name = "USER_ID", unique = true, nullable = false, length = 30)
    private String userId;

    @Column(name = "USER_PWD", nullable = false, length = 30)
    private String userPwd;

    @Column(name = "USER_NAME", nullable = false, length = 30)
    private String userName;

    @Column(name = "USER_PHONE", nullable = false, length = 15)
    private String userPhone;

    @Column(name = "USER_EMAIL", nullable = false, length = 50)
    private String userEmail;

    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Plan> plans = new ArrayList<>();

    // 정보 수정을 위한 메소드
    public void update(String userName, String userPhone, String userEmail) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
    }
}
