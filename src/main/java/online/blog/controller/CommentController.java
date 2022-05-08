package online.blog.controller;

import lombok.extern.slf4j.Slf4j;
import online.blog.entity.Comment;
import online.blog.entity.User;
import online.blog.queryvo.DetailedBlog;
import online.blog.service.BlogService;
import online.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @description: 评论控制器
 * @date 2022/04/17
 */
@Slf4j
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Value("${comment.avatar}")
    private String avatar;

    /**
     * @param blogId
     * @description: 查询单篇博客的评论列表
     * @date 2022/04/17
     */
    @GetMapping("/comments/{blogId}")
    public ModelAndView comments(@PathVariable Long blogId) {
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        log.debug("Blog' comments,{}", comments);

        ModelAndView mv = new ModelAndView();
        mv.addObject("comments", comments);
        mv.setViewName("blog :: commentList");
        return mv;
    }

    /**
     * @param comment,session,model
     * @description: 博客下新增评论，包括新增回复评论和新增初始评论
     * @date 2022/04/17
     */
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session, Model model) {
        Long blogId = comment.getBlogId();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            comment.setAvatar(avatar);// 设置头像
        }

        if (comment.getParentComment().getId() != null) {
            comment.setParentCommentId(comment.getParentComment().getId());
        }
        commentService.saveComment(comment);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }

    /**
     * @param blogId,comment
     * @description: 删除评论，只有管理员有删除权限
     * @date 2022/04/17
     */
    @GetMapping("/comment/{blogId}/{id}/delete")
    public String delete(@PathVariable("blogId") Long blogId, @PathVariable Long id, Comment comment) {
        commentService.deleteComment(comment, id);
        // 删除评论后转发到博客详情页
        return "forward:/blog/" + blogId;
    }

}