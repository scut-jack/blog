package online.blog.dao;

import online.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author jack
 * @date 2020/7/1-16:21
 */
@Mapper //让Mybatis找到对应的mapper，在编译的时候动态生成代理类，实现相应SQL功能
@Repository
public interface UserDao {
    /**
     * @Description:
     * @Param: username:用户名；password:密码
     * @Return: 返回用户对象
     */
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);//@Param注解：将参数传递给SQL
}
