package online.blog.dao;

import online.blog.entity.Blog;
import online.blog.queryvo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 博客持久层接口
 */
@Mapper
@Repository
public interface BlogDao {

    ShowBlog getBlogById(Long id);

    List<Blog> getAllBlog();

    List<BlogQuery> getAllBlogQuery();

    int saveBlog(Blog blog);

    int updateBlog(ShowBlog showBlog);

    void deleteBlog(Long id);

    List<BlogQuery> searchByTitleOrTypeOrRecommend(SearchBlog searchBlog);

    //查询首页最新博客列表信息
    List<FirstPageBlog> getFirstPageBlog();
    //查询首页最新推荐信息
    List<RecommendBlog> getAllRecommendBlog();

//    List<FirstPageBlog> getNewBlog();
    //搜索博客列表
    List<FirstPageBlog> getSearchBlog(String query);

    DetailedBlog getDetailedBlog(Long id);

    int updateViews(Long id);

//    根据博客id查询出评论数量
    int getCommentCountById(Long id);

    List<FirstPageBlog> getByTypeId(Long typeId);
    //统计博客总数
    Integer getBlogTotal();
    //统计访问总数
    Integer getBlogViewTotal();
    //统计评论总数
    Integer getBlogCommentTotal();
    //统计留言总数
    Integer getBlogMessageTotal();
}