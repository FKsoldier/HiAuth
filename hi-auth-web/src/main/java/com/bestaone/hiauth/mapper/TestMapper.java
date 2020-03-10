package com.bestaone.hiauth.mapper;

import com.bestaone.hiauth.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface TestMapper {

    @Select("select userId from sys_user_client where clientId = #{clientId}")
    List<String> UserIdByClientId(String clientId);

    @Select("select * from sys_user where id = #{userId}")
    User findByUserId(String userId);
}
