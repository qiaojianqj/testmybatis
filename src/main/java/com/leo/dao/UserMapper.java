package com.leo.dao;

import com.leo.domain.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    //@Select注解等同于UserMapper.xml中的 select 标签
    //@Select("SELECT * FROM user WHERE id = #{id}")
    User getUser(int id);
}