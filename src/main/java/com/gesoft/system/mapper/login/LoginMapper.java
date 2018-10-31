package com.gesoft.system.mapper.login;

import com.gesoft.system.po.user.UserPo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface LoginMapper {


    @Results({
            @Result( property = "loginName" ,column = "loginName"),
            @Result( property = "passWord" ,column = "passWord"),
            @Result( property = "age" ,column = "age"),
//            @Result( property = "inputDate" ,column = "inputDate" ),
            @Result( property = "inputName" ,column = "inputName"),
            @Result( property = "id" ,column = "id"),
            @Result( property = "delsign" ,column = "delsign"),
    })
    @Select("select * from user_t  where delsign = 0" +
            " and loginName = #{loginName} " +
            "and  passWord = #{passWord} ")
    public UserPo login(UserPo user);
}
