package com.gesoft.system.controller.article;

import com.gesoft.system.po.article.ArticlePo;
import com.gesoft.system.service.article.IArticleService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.gesoft.system.common.DataUtils.getUid;

@RestController
@RequestMapping("api/article")
public class ApiArticleController {

    @Resource
    private IArticleService articleService;



    @PostMapping("articleList")
    String apiArticleList(){

        List<ArticlePo> list = articleService.findAll();
        JSONObject json = JSONObject.fromObject("{\"result\":\"true\"}");
        json.put("list",list);
        return json.toString();
    }

    @PostMapping("article")
    String apiArticle(ArticlePo article){

        article = articleService.findArticle(article);
        if(null !=article){
            JSONObject json = JSONObject.fromObject("{\"result\":\"true\"}");
            json.put("article",article );
            return json.toString();
        }else{
            JSONObject json = JSONObject.fromObject("{\"result\":\"false\"}");
            return json.toString();
        }




    }



}
