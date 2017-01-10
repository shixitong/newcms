package com.quick4j.web.service;

import com.quick4j.core.feature.orm.mybatis.Page;
import com.quick4j.core.generic.GenericService;
import com.quick4j.web.model.User;

import java.util.List;

/**
 * 用户 业务 接口
 * 
 * @author StarZou
 * @since 2014年7月5日 上午11:53:33
 **/
public interface UserService extends GenericService<User, Long> {

    /**
     * 用户验证
     * 
     * @param user
     * @return
     */
    User authentication(User user);

    /**
     * 根据用户名查询用户
     * 
     * @param username
     * @return
     */
    User selectByUsername(String username);

    List<User> selectByPage(Page page);
}