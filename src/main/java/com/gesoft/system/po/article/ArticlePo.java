package com.gesoft.system.po.article;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("article")
@Data
public class ArticlePo {

    private String id ="";
    private String title = "";
    private String content ="";
    private String picPath ="";
    private String inputName = "";

}
