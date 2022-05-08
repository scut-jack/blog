package online.blog.service;

import online.blog.entity.Blog;
import online.blog.queryvo.*;

import java.util.List;

/**
 * @Description: 博客列表业务层接口
 */
public interface BlogService {

    /**
     * @description: 查询所有博客, 按更新时间倒排, 首页最新博客栏展示
     * @date 2022/04/17
     */
    List<FirstPageBlog> getAllFirstPageBlog();

    /**
     * @param recommendedCount 需展示的推荐博客数目
     * @description: 查询首页推荐博客栏需展示的博客
     * @date 2022/04/17
     */
    List<RecommendBlog> getRecommendedBlog(Integer recommendedCount);

    /**
     * @param query 关键字
     * @description: 根据关键字搜索数据库中相关博客(标题或者内容包含关键字)
     * @date 2022/04/17
     */
    List<FirstPageBlog> getSearchBlog(String query);

    /**
     * @param id
     * @description: 查询单篇博客展示所有信息：标题,内容,分类,博客创建者,创建时间...(具体参考DetailedBlog类)
     * @date 2022/04/17
     */
    DetailedBlog getDetailedBlog(Long id);

    /**
     * @description: 网站信息：文章总数
     * @date 2022/04/17
     */
    Integer getBlogTotal();

    /**
     * @description: 网站信息：访问总数
     * @date 2022/04/17
     */
    Integer getBlogViewTotal();

    /**
     * @description: 网站信息：评论总数
     * @date 2022/04/17
     */
    Integer getBlogCommentTotal();

    /**
     * @description: 网站信息：留言总数
     * @date 2022/04/17
     */
    Integer getBlogMessageTotal();

    /**
     * @param typeId
     * @description: 根据TypeId获取博客列表，在分类页进行展示
     * @date 2022/04/17
     */
    List<FirstPageBlog> getByTypeId(Long typeId);

    ShowBlog getBlogById(Long id);

    List<BlogQuery> getAllBlog();

    int saveBlog(Blog blog);

    int updateBlog(ShowBlog showBlog);

    void deleteBlog(Long id);

    List<BlogQuery> getBlogBySearch(SearchBlog searchBlog);

}