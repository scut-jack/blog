package online.blog.service.impl;

import lombok.extern.slf4j.Slf4j;
import online.blog.NotFoundException;
import online.blog.dao.BlogDao;
import online.blog.entity.Blog;
import online.blog.queryvo.*;
import online.blog.service.BlogService;
import online.blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: 博客列表业务层接口实现类
 */
@Slf4j
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    /**
     * @description: 查询所有博客, 按更新时间倒排, 首页最新博客栏展示
     * @date 2022/04/17
     */
    @Override
    public List<FirstPageBlog> getAllFirstPageBlog() {
        return blogDao.getFirstPageBlog();
    }

    /**
     * @param recommendedCount 需展示的推荐博客数目
     * @description: 查询首页推荐博客栏需展示的博客
     * @date 2022/04/17
     */
    @Override
    public List<RecommendBlog> getRecommendedBlog(Integer recommendedCount) {
        List<RecommendBlog> allRecommendBlog = blogDao.getAllRecommendBlog(recommendedCount);
        return allRecommendBlog;
    }

    /**
     * @param query 关键字
     * @description: 根据关键字搜索数据库中相关博客(标题或者内容包含关键字)
     * @date 2022/04/17
     */
    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    /**
     * @param id
     * @description: 查询单篇博客展示所有信息：标题,内容,分类,博客创建者,创建时间...(具体参考DetailedBlog类)
     * @date 2022/04/17
     */
    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        log.info("Request blog id is {}", id);
        DetailedBlog detailedBlog = blogDao.getDetailedBlog(id);
        if (detailedBlog == null) {
            log.error("The blog is not existed.");
            throw new NotFoundException("The blog is not existed.");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        // 文章访问数量自增
        blogDao.updateViews(id);
        log.debug("Detail blog: {}", detailedBlog);
        log.info("Get detailed blog successfully.");
        return detailedBlog;
    }

    /**
     * @description: 网站信息：文章总数
     * @date 2022/04/17
     */
    @Override
    public Integer getBlogTotal() {
        return blogDao.getBlogTotal();
    }

    /**
     * @description: 网站信息：访问总数
     * @date 2022/04/17
     */
    @Override
    public Integer getBlogViewTotal() {
        return blogDao.getBlogViewTotal();
    }

    /**
     * @description: 网站信息：评论总数
     * @date 2022/04/17
     */
    @Override
    public Integer getBlogCommentTotal() {
        return blogDao.getBlogCommentTotal();
    }

    /**
     * @description: 网站信息：留言总数
     * @date 2022/04/17
     */
    @Override
    public Integer getBlogMessageTotal() {
        return blogDao.getBlogMessageTotal();
    }


    @Override
    public ShowBlog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    /**
     * @description: 后台管理页面, 查询博客简单信息列表
     * @date 2022/05/08
     */
    @Override
    public List<BlogQuery> getAllBlog() {
        return blogDao.getAllBlogQuery();
    }

    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setCommentCount(0);
        return blogDao.saveBlog(blog);
    }

    @Override
    public int updateBlog(ShowBlog showBlog) {
        showBlog.setUpdateTime(new Date());
        return blogDao.updateBlog(showBlog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
    }

    @Override
    public List<BlogQuery> getBlogBySearch(SearchBlog searchBlog) {
        return blogDao.searchByTitleOrTypeOrRecommend(searchBlog);
    }


//    @Override
//    public List<FirstPageBlog> getNewBlog() {
//        return blogDao.getNewBlog();
//    }


    @Override
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        return blogDao.getByTypeId(typeId);
    }


}