package com.gesoft.system.service.article.impl;

import com.gesoft.system.mapper.article.ArticleMapper;
import com.gesoft.system.po.article.ArticlePo;
import com.gesoft.system.service.article.IArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleService implements IArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public List<ArticlePo> findAll() {
        return articleMapper.findAll();
    }

    @Override
    public ArticlePo findArticle(ArticlePo article) {
        return articleMapper.findArticle(article);
    }

    @Override
    public void addArticle(ArticlePo article) {
        articleMapper.addArticle(article);
    }


    @Override
    public void delArticle(ArticlePo article) {
        articleMapper.delArticle(article);
    }

    @Override
    public void updArticle(ArticlePo article) {
        articleMapper.updArticle(article);
    }
}
