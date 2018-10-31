package com.gesoft.system.mapper.article;

import com.gesoft.system.po.article.ArticlePo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleMapper {

    @Select("SELECT * from article_t where delsign = 0")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "title",column = "title"),
            @Result(property = "content",column = "content"),
            @Result(property = "picPath",column = "picPath")
    })
    List<ArticlePo> findAll();

    @Select("SELECT * from article_t where delsign = 0 and id = '${id}'")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "title",column = "title"),
            @Result(property = "content",column = "content"),
            @Result(property = "picPath",column = "picPath")
    })
    ArticlePo findArticle(ArticlePo article);

    @Insert("INSERT INTO article_t(id,inputDate,inputName,title,content,picPath) VALUES(#{id},now(),#{inputName},#{title}, #{content},#{picPath})")
    void addArticle(ArticlePo article);


    @Update("UPDATE article_t  SET delsign = 1 where id = #{id} ")
    void delArticle(ArticlePo article);



    @Update("UPDATE article_t  SET title = #{title} ,content = #{content} , picPath = #{picPath} where id = #{id} ")
    void updArticle(ArticlePo article);


}
