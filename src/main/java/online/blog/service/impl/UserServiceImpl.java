package online.blog.service.impl;

/**
 * @author jack
 * @date 2020/7/1-16:29
 */

import online.blog.dao.UserDao;
import online.blog.entity.User;
import online.blog.service.UserService;
import online.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户业务层接口实现类
 * @Date: Created in 23:01 2020/5/26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * @Description:
     * @Param: username:用户名；password:密码
     * @Return: 返回用户对象
     * 这里主要是获取数据库中的用户名和密码，通过控制器传递过来的密码进行解析匹配，匹配成功则登录
     */
    @Override
    public User checkUser(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
