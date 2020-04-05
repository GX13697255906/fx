package com.gx.fm.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gx.fm.config.datasources.DataSource;
import com.gx.fm.config.datasources.DataSourceContextHolder;
import com.gx.fm.config.datasources.DataSourceType;
import com.gx.fm.constants.ParametersCenter;
import com.gx.fm.entity.User;
import com.gx.fm.mapper.UserMapper;
import com.gx.fm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xun.guo
 * @since 2019-12-31
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    @DataSource(DataSourceType.DataSource_FM)
    public List<User> selUserList() {
        log.info("获取所有用户信息", DateUtil.format(new Date(), ParametersCenter.Date_Format));
        return this.list();
    }

    @Override
    public boolean addUser(User user) {
        log.info("添加用户信息", DateUtil.format(new Date(), ParametersCenter.Date_Format));

        DataSourceContextHolder.setDataSource(DataSourceType.DataSource_TB);
        boolean result = this.save(user);

        DataSourceContextHolder.setDataSource(DataSourceType.DataSource_FM);
        result = this.save(user);

        return result;
    }

    @Override
    public User selUserByUserName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(User::getUsername, username)
                .eq(User::getStatus, 0);
        User user = this.getOne(queryWrapper);
        return user;
    }
}
