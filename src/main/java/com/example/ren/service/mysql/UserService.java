package com.example.ren.service.mysql;

import com.example.ren.dao.mysql.UserRepository;
import com.example.ren.dao.redis.UserRedis;
import com.example.ren.model.mysql.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户处理逻辑
 *
 * @author qiang.ren
 * @date 2018/4/23
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRedis userRedis;

    private static final String KEY_HEAD = "mysql-user";

    public User findById(Long id){
        User user = userRedis.get(KEY_HEAD + id);
        if (null == user && userRepository.findById(id).isPresent()){
            user = userRepository.findById(id).get();
            userRedis.add(KEY_HEAD + id, 30L, user);
        }
        return user;
    }

    public User create(User user){
        User user1 = userRepository.save(user);
        userRedis.add(KEY_HEAD + user1.getId(), 30L, user1);
        return user1;
    }

    public User update(User user){
        User result = null;
        if (null != user){
            userRedis.delete(KEY_HEAD + user.getId());
            userRedis.add(KEY_HEAD + user.getId(), 30L, user);
            result = userRepository.save(user);
        }
        return result;
    }

    public void delete(Long id){
        userRedis.delete(KEY_HEAD + id);
        userRepository.deleteById(id);
    }
}
