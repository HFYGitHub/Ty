package com.ty.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ty.common.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
