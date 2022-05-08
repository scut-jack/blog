package online.blog.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 分类实体类
 */
public class Type {

    private Long id;
    private String name;// 分类名称

    private List<Blog> blogs = new ArrayList<>();// 该分类下的博客

    public Type() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blogs=" + blogs +
                '}';
    }
}