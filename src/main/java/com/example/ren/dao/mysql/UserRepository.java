package com.example.ren.dao.mysql;

import com.example.ren.model.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户持久化
 *
 * @author qiang.ren
 * @date 2018/4/16
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
