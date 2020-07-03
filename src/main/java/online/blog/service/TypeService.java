package online.blog.service;

import online.blog.entity.Type;
import java.util.List;


/**
 * @Description: 文章分类业务层接口
 */
public interface TypeService {

    //新增保存分类
    int saveType(Type type);

    //根据id查询分类
    Type getType(Long id);

    //查询所有分类
    List<Type> getAllType();

    //根据分类名称查询分类
    Type getTypeByName(String name);

    //编辑修改分类
    int updateType(Type type);

    List<Type> getAllTypeAndBlog();

    //删除分类
    void deleteType(Long id);
}