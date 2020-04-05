package com.gx.fm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gx.fm.config.datasources.DataSource;
import com.gx.fm.config.datasources.DataSourceType;
import com.gx.fm.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xun.guo
 * @since 2019-12-31
 */
public interface UserService extends IService<User> {

    List<User> selUserList();

    boolean addUser(User user);

    User selUserByUserName(String username);


}
