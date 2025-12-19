package com.kh.Calendar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_NO")
    private Long categoryNo;

    @Column(name = "CATEGORY_NAME", nullable = false)
    private String categoryName;

    // Plan과의 관계 설정, 주인은 plan
     @OneToMany(mappedBy = "category")
     private List<Plan> plans = new ArrayList<>();
}