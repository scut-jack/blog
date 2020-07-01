package online.blog.service;

import online.blog.mapper.UserMapper;
import online.blog.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jack
 * @date 2020/7/1-13:47
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService{
        @Resource
        private UserMapper userMapper;

        @Override
        public List<User> getAllUser() {
            return userMapper.getAllUser();
        }
}
