package com.example.ren.dao.mysql;

import com.example.ren.model.mysql.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 部门持久化
 *
 * @author qiang.ren
 * @date 2018/4/16
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
