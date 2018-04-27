package com.example.ren.dao.mysql;

import com.example.ren.model.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * 用户持久化
 *
 * @author qiang.ren
 * @date 2018/4/16
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @Query(value = "select t from User t where t.name like ?1 ")
    User findByName(@Param("name") String name);

    @Query(value = "select t from User t where t.name = ?1 and t.email = ?2")
    User findByNameAndEmail(@Param("name") String name, @Param("email") String email);

}
