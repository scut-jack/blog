package online.blog.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import online.blog.dao.BlogDao;
import online.blog.dao.CommentDao;
import online.blog.entity.Comment;
import online.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: 博客评论业务层接口实现类
 * @date 2022/04/17
 */
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private BlogDao blogDao;

    /**
     * @param blogId 博客ID，标识一篇博客
     * @description: 查询单篇博客下的所有评论，单条评论的数据结构：评论 + 回复列表
     * @date 2022/04/17
     */
    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        // 查询出父节点列表
        List<Comment> comments = commentDao.findCommentsByBlogId(blogId, -1l);
        List<Comment> replyList = new ArrayList<>();// 评论回复
        if (!CollectionUtil.isEmpty(comments)) {
            for (Comment c : comments) {
                if (c != null) {
                    processReply(replyList, blogId, c.getId(), c.getNickname());
                    c.setReplyComments(replyList);
                    replyList = new ArrayList<>();// 每条评论都需管理一个评论回复列表
                }
            }
        }
        log.debug("Blog's comments:{}", comments);
        return comments;
    }

    private void processReply(List<Comment> replyList, Long blogId, Long commentId, String parentNickname) {
        List<Comment> childComments = commentDao.findCommentsByBlogId(blogId, commentId);
        if (!CollectionUtil.isEmpty(childComments)) {
            replyList.addAll(childComments);
            for (Comment c : childComments) {
                if (c != null) {
                    c.setParentNickname(parentNickname);
                    processReply(replyList, blogId, c.getId(), c.getNickname());
                }
            }
        }
    }

    /**
     * @param comment
     * @description: 新增评论
     * @date 2022/04/17
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        int comments = commentDao.saveComment(comment);
        log.info("Save the comment successfully.");
        //文章评论计数,查询出文章评论数量并更新
        blogDao.getCommentCountById(comment.getBlogId());
        log.info("Blog comment number updated successfully.");
        return comments;
    }

    /**
     * @param comment,id
     * @description: 删除评论
     * @date 2022/04/17
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteComment(Comment comment, Long id) {
        commentDao.deleteComment(id);
        log.info("The comment has been deleted.");
        blogDao.getCommentCountById(comment.getBlogId());
        log.info("Blog comment number minus one.");
    }
}