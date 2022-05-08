package online.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import online.blog.entity.Comment;
import online.blog.queryvo.DetailedBlog;
import online.blog.queryvo.FirstPageBlog;
import online.blog.queryvo.RecommendBlog;
import online.blog.service.BlogService;
import online.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @description: 渲染博客首页: 推荐博客(默认阅读量最多的4篇) + 最新博客(分页展示所有博客,每页5篇) + 网站信息
 * @date 2022/04/17
 */
@Slf4j
@Controller
public class IndexController {

    @Value("${front.pageSize:5}")
    private Integer frontBlogPageSize;// 首页展示最新博客数目，默认每页展示5条博客

    @Value("${recommended.blog:4}")
    private Integer recommendedBlogCount;// 首页展示特别推荐博客数目，默认4条推荐博客

    @Value("${search.blog:5}")
    private Integer searchBlogCount;// 搜索博客单页数目，默认100条博客

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    /**
     * @param pageNum
     * @description: 分页查询博客列表，首页展示
     * @date 2022/04/16
     */
    @GetMapping("/")
    public ModelAndView index(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, frontBlogPageSize);// 采用分页插件对查询结果进行分页
        List<FirstPageBlog> allFirstPageBlog = blogService.getAllFirstPageBlog();// 查询最新博客
        List<RecommendBlog> recommendedBlog = blogService.getRecommendedBlog(recommendedBlogCount);// 查询推荐博客

        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(allFirstPageBlog);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("recommendedBlogs", recommendedBlog);
        mv.setViewName("index");
        log.info("Successfully query new front blog count:{}, recommended blog count:{}", allFirstPageBlog.size(), recommendedBlog.size());

        return mv;
    }

    /**
     * @param pageNum,query 查询关键字
     * @description: 根据用户输入的关键字在博客表t_blog中对title和content字段进行模糊搜索
     * 博客数量过多时会存在性能问题？关键字不能出错 否则搜不到？
     * @date 2022/04/16
     */
    @RequestMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam String query) {
        PageHelper.startPage(pageNum, searchBlogCount);
        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        log.info("Successfully search blog count: {}, search keywords:{}", searchBlog.size(), query);

        return "search";
    }

    /**
     * @param id
     * @description: 跳转博客详情页面展示
     * @date 2022/04/17
     */
    @GetMapping("/blog/{id}")
    public ModelAndView blog(@PathVariable Long id) {
        DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
        List<Comment> comments = commentService.listCommentByBlogId(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("comments", comments);
        mv.addObject("blog", detailedBlog);
        mv.setViewName("blog");
        return mv;
    }

    /**
     * @description: 该网站统计的博客相关信息：本站博客总数,访问博客次数,评论总数,留言总数
     * @date 2022/04/17
     */
    @GetMapping("/footer/blogmessage")
    public ModelAndView blogMessage() {
        int blogTotal = blogService.getBlogTotal();
        int blogViewTotal = blogService.getBlogViewTotal();
        int blogCommentTotal = blogService.getBlogCommentTotal();
        int blogMessageTotal = blogService.getBlogMessageTotal();
        log.info("Blog messages, blogTotal:{}, blogViewTotal:{}, blogCommentTotal:{}, blogMessageTotal:{}",
                blogTotal, blogViewTotal, blogCommentTotal, blogMessageTotal);

        ModelAndView mv = new ModelAndView();
        mv.addObject("blogTotal", blogTotal);
        mv.addObject("blogViewTotal", blogViewTotal);
        mv.addObject("blogCommentTotal", blogCommentTotal);
        mv.addObject("blogMessageTotal", blogMessageTotal);
        mv.setViewName("index :: blogMessage");
        return mv;
    }
}