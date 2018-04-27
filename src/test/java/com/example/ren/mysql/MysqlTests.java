package com.example.ren.mysql;

import com.example.ren.dao.mysql.DepartmentRepository;
import com.example.ren.dao.mysql.RoleRepository;
import com.example.ren.dao.mysql.UserRepository;
import com.example.ren.model.mysql.Department;
import com.example.ren.model.mysql.Role;
import com.example.ren.model.mysql.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Mysql测试(需要注意，进行mysql测试时，需要注释掉Neo4j配置类->Neo4jConfiguration.java)
 * Created by qiang.ren on 2018/4/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MysqlTests {

    private static Logger logger = LoggerFactory.getLogger(MysqlTests.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Before
    public void initData(){
        userRepository.deleteAll();
        roleRepository.deleteAll();
        departmentRepository.deleteAll();

        Department department = new Department();
        department.setName("开发部");
        departmentRepository.save(department);
        Assert.assertNotNull(department.getId());

        Role role = new Role();
        role.setName("admin");
        roleRepository.save(role);
        Assert.assertNotNull(role.getId());

        User user = new User();
        user.setName("任强");
        user.setEmail("qiang.ren@phicomm.com");
        user.setCreateDate(new Date());
        user.setDepartment(department);
        List<Role> roles = roleRepository.findAll();
        Assert.assertNotNull(roles);
        user.setRoles(roles);
        userRepository.save(user);
        Assert.assertNotNull(user);
    }

    @Test
    public void findPage(){
        Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "id"));
        Page<User> page = userRepository.findAll(pageable);
        Assert.assertNotNull(page);
        for (User user:page.getContent()) {
            logger.info("====user==== user name:{}, user email:{}, department name:{}, role name:{}",
                    user.getName(), user.getEmail(), user.getDepartment().getName(), user.getRoles().get(0).getName());
        }

        User u = userRepository.findByNameAndEmail("任强", "qiang.ren@phicomm.com");
        logger.info("====u==== u name:{},  user email:{}, department name:{}, role name:{}",
                u.getName(), u.getEmail(), u.getDepartment().getName(), u.getRoles().get(0).getName());
    }
}
