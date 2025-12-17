package com.kh.Calendar.repository;

import com.kh.Calendar.entity.Plan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlanRepositoryImpl implements PlanRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Plan save(Plan plan) {
        em.persist(plan);
        return plan;
    }

    @Override
    public Optional<Plan> findByPlanNo(Long planNo) {
        Plan plan = em.find(Plan.class, planNo);
        return Optional.ofNullable(plan);
    }

    @Override
    public void delete(Plan plan) {
        em.remove(plan);
    }

    @Override
    public List<Plan> searchPlans(Long userNo, String date, String keyword) {
        // JPQL을 사용하여 동적 쿼리 구성 (keyword가 있을 수도 있고 없을 수도 있음)
        String jpql = "select p from Plan p where p.user.userNo = :userNo and p.date = :date";
        if (keyword != null && !keyword.trim().isEmpty()) {
            jpql += " and p.planTitle like :keyword";
        }

        var query = em.createQuery(jpql, Plan.class);
        query.setParameter("userNo", userNo);
        query.setParameter("date", date);

        if (keyword != null && !keyword.trim().isEmpty()) {
            query.setParameter("keyword", "%" + keyword + "%");
        }

        return query.getResultList();
    }
}
