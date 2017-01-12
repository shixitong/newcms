package com.newcms.web.service;

import com.newcms.core.feature.orm.mybatis.Page;
import com.newcms.core.generic.GenericService;
import com.newcms.web.model.User;

import java.util.List;

/**
 * 用户 业务 接口
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
