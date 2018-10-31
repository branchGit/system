package com.gesoft.system.po.user;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("user")
@Data
public class UserPo {


    private String id;
    private String loginName;
    private String passWord;
    private int age;
    private int delsign;
    private String inputDate;
    private String inputName;
}
