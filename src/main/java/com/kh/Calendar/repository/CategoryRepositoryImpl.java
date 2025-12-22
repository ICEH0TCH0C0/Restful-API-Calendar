package com.kh.Calendar.repository;

import com.kh.Calendar.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Category> findAll() {
        // JPQL을 사용하여 전체 목록 조회
        return em.createQuery("select c from Category c", Category.class)
                .getResultList();
    }

    @Override
    public Optional<Category> findById(Long categoryNo) {
        Category category = em.find(Category.class, categoryNo);
        return Optional.ofNullable(category);
    }

    @Override
    public Optional<Category> findByName(String categoryName) {
        List<Category> result = em.createQuery("select c from Category c where c.categoryName = :categoryName", Category.class)
                .setParameter("categoryName", categoryName)
                .getResultList();
        return result.stream().findFirst();
    }

    @Override
    public Category save(Category category) {
        em.persist(category);
        return category;
    }
}