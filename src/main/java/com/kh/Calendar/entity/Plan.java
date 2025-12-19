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

    @Setter //setter를 카테고리만 적용을 위해 선언
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_NO") // nullable = true로 두어 카테고리 없이도 저장 가능하게 함
    private Category category;

    // 정보 수정을 위한 메소드
    public void update(String planTitle, String planContent, Category category) {
        this.planTitle = planTitle;
        this.planContent = planContent;
        this.category = category;
    }

}
