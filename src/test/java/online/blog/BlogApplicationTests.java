package online.blog;


import online.blog.dao.CommentDao;
import online.blog.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private CommentDao commentDao;

    @Test
    void contextLoads() {
        List<Comment> comments = commentDao.findCommentsByBlogId(67l, -1l);
        System.out.println(comments);
    }
}
