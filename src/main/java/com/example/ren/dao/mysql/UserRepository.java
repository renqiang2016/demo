package com.example.ren.dao.mysql;

import com.example.ren.model.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 用户持久化
 *
 * @author qiang.ren
 * @date 2018/4/16
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByName(@Param("name") String name);
}
