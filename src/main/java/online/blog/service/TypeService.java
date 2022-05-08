package online.blog.service;

import online.blog.entity.Type;

import java.util.List;


/**
 * @Description: 文章分类业务层接口
 */
public interface TypeService {

    /**
     * @description: 查询库中所有分类, 以及分类下的所有博客
     * @date 2022/04/17
     */
    List<Type> getAllTypeAndBlog();

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


    //删除分类
    void deleteType(Long id);
}