package com.kh.Calendar.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

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

    @CreatedDate
    @Column(name = "DATE", nullable = false)
    private String date;

    @Column(name = "PLAN_CONTENT", nullable = false, length = 100)
    private String planContent;

}
