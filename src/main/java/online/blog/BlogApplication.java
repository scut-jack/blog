package online.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableTransactionManagement   //开启事务管理
//@MapperScan("online.blog.dao")//与dao层的@Mapper二选一写上即可(主要作用是扫包)
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
