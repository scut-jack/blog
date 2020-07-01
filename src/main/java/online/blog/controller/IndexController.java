package online.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author jack
 * @date 2020/7/1-15:50
 */
@Controller
public class IndexController {
    //通过get方式请求路径
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
