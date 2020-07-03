package online.blog.service;

import online.blog.entity.Comment;

import java.util.List;

/**
 * @Description: 博客评论业务层接口
 */
public interface CommentService {
    //根据博客id查询评论信息
    List<Comment> listCommentByBlogId(Long blogId);
    //添加保存评论
    int saveComment(Comment comment);

//    查询子评论
//    List<Comment> getChildComment(Long blogId,Long id);

    //删除评论
    void deleteComment(Comment comment, Long id);

}