package com.gesoft.system.controller.article;

import com.gesoft.system.controller.BaseController;
import com.gesoft.system.po.article.ArticlePo;
import com.gesoft.system.po.user.UserPo;
import com.gesoft.system.service.article.IArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.gesoft.system.common.DataUtils.getUid;

@Controller
@RequestMapping("article")
public class ArticleController extends BaseController {

    @Resource
    private IArticleService articleService;



    @RequestMapping("index")
    String index(Model model){

        List<ArticlePo> list = articleService.findAll();
        model.addAttribute("articleList",list);
        return "article/index";

    }

    @RequestMapping("edit")
    String edit(ArticlePo article,Model model){

        article = articleService.findArticle(article);
        if (null==article){
            article = new ArticlePo();
        }
        model.addAttribute("article",article);
        return "article/edit";
    }

    @RequestMapping("save")
    String save(ArticlePo article){


        article.setInputName(getUser().getId());
        if ("".equals(article.getId())){
            article.setId(getUid());
            articleService.addArticle(article);
        }else{
            articleService.updArticle(article);
        }




        return "redirect:/article/index";
    }


    @RequestMapping("del")
    String del(ArticlePo article){

        articleService.delArticle(article);


        return "redirect:/article/index";
    }




}
