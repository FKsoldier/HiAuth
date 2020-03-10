package com.bestaone.himall.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface TestMapper {

    @Select("select * from sys_user_client")
    String test();

    @Select("select id from sys_user_client where userId = #{userId} and clientId = #{clientId}")
    String checkRoleByUserId(@Param("userId") String userId, @Param("clientId") String clientId);
}
