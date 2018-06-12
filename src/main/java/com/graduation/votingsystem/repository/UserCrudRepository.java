package com.graduation.votingsystem.repository;

import com.graduation.votingsystem.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface UserCrudRepository extends JpaRepository<User, Integer>{
    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    User save(User user);

    Optional<User> findById(Integer id);

    @Query("SELECT DISTINCT u from User u JOIN FETCH u.role r ORDER BY u.email")
    List<User> getAll();

    User getByEmail(String email);
}
