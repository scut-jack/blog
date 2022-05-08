package online.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import online.blog.entity.Type;
import online.blog.queryvo.FirstPageBlog;
import online.blog.service.BlogService;
import online.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @description: 分类页面显示控制器
 * @date 2022/04/17
 */
@Slf4j
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

//    分页查询分类

    /**
     * @param pageNum,id
     * @description: 首页分类展示博客, 注意创建博客必须属于某个分类
     * @date 2022/04/17
     */
    @GetMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @PathVariable Long id, Model model) {
        List<Type> types = typeService.getAllTypeAndBlog();// 查询出库中所有分类
        log.info("Types, {}", types);
        //-1表示从首页导航点进来的
        if (id == -1) {
            id = types.get(0).getId();
        }
        // 默认单个分类下面展示最多5条博客
        PageHelper.startPage(pageNum, 5);
        List<FirstPageBlog> blogs = blogService.getByTypeId(id);

        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }

}