package com.kh.Calendar.repository;

import com.kh.Calendar.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findByUserNo(Long userNo) {
        // em.find는 fetch join을 직접 지정하기 어려우므로 JPQL로 변경
        return em.createQuery("select u from User u left join fetch u.plans where u.userNo = :userNo", User.class)
                .setParameter("userNo", userNo)
                .getResultStream().findFirst();
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return em.createQuery("select u from User u where u.userId = :userId", User.class)
                .setParameter("userId", userId)
                .getResultStream().findFirst();
    }

    @Override
    public Optional<String> findUserId(String userName, String userPhone) {
        return em.createQuery("select u.userId from User u where u.userName = :userName and u.userPhone = :userPhone", String.class)
                .setParameter("userName", userName)
                .setParameter("userPhone", userPhone)
                .getResultStream().findFirst();
    }

    @Override
    public Optional<User> findByUserIdAndUserNameAndUserPhone(String userId, String userName, String userPhone) {
        return em.createQuery("select u from User u where u.userId = :userId and u.userName = :userName and u.userPhone = :userPhone", User.class)
                .setParameter("userId", userId)
                .setParameter("userName", userName)
                .setParameter("userPhone", userPhone)
                .getResultStream().findFirst();
    }

    @Override
    public void delete(User user) {
        em.remove(user);
    }
}
