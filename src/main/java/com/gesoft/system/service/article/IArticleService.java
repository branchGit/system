package com.gesoft.system.service.article;

import com.gesoft.system.po.article.ArticlePo;

import java.util.List;

public interface IArticleService {

    List<ArticlePo> findAll();
    ArticlePo findArticle(ArticlePo article);

    void addArticle(ArticlePo article);
    void delArticle(ArticlePo article);

    void updArticle(ArticlePo article);
}
