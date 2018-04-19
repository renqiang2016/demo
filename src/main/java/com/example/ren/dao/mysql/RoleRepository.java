package com.example.ren.dao.mysql;

import com.example.ren.model.mysql.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 角色持久化
 * Created by qiang.ren on 2018/4/16.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
