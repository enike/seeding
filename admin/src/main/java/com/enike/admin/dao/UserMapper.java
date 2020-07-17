package com.enike.admin.dao;

import com.enike.admin.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author enike
 * @since 2020-07-17
 */
public interface UserMapper extends BaseMapper<User> {


    User selectByName(String username);

    Set<String> getPermissionsByName(String username);
}
