package com.kh.Calendar.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "PLAN")
public class Plan {
    private Long planNo;
    private Long userNo;
    private String planTitle;
    private String startDate;
    private String endDate;
    private String planContent;


}
