package online.blog.dao;

import online.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentDao {

    /**
     * @param blogId,commentId
     * @description: 查询评论
     * @date 2022/04/17
     */
    List<Comment> findCommentsByBlogId(@Param("blogId") Long blogId, @Param("commentId") Long commentId);

    /**
     * @param comment
     * @description: 添加评论
     * @date 2022/04/17
     */
    int saveComment(Comment comment);

    /**
     * @param id
     * @description: 删除评论
     * @date 2022/04/17
     */
    void deleteComment(Long id);

}