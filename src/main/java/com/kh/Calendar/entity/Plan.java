package com.kh.Calendar.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "PLAN")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAN_NO")
    private Long planNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_NO", nullable = false)
    private User user;

    @Column(name = "PLAN_TITLE", nullable = false, length = 30)
    private String planTitle;

    @Column(name = "DATE", nullable = false)
    private String date;

    @Column(name = "PLAN_CONTENT", nullable = false, length = 100)
    private String planContent;

    // 정보 수정을 위한 메소드
    public void update(String planTitle, String planContent) {
        this.planTitle = planTitle;
        this.planContent = planContent;
    }
}
