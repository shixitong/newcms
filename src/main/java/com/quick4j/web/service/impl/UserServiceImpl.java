package com.quick4j.web.service.impl;

import com.quick4j.core.feature.orm.mybatis.Page;
import com.quick4j.core.generic.GenericDao;
import com.quick4j.core.generic.GenericServiceImpl;
import com.quick4j.web.dao.UserMapper;
import com.quick4j.web.model.User;
import com.quick4j.web.model.UserExample;
import com.quick4j.web.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户Service实现类
 *
 * @author StarZou
 * @since 2014年7月5日 上午11:54:24
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int insert(User model) {
        return userMapper.insertSelective(model);
    }

    @Override
    public int update(User model) {
        return userMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User authentication(User user) {
        return userMapper.authentication(user);
    }

    @Override
    public User selectById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public GenericDao<User, Long> getDao() {
        return userMapper;
    }

    @Override
    public User selectByUsername(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        final List<User> list = userMapper.selectByExample(example);
        return list.get(0);
    }

    @Override
    public List<User> selectByPage(Page page) {

        List<User> list = userMapper.selectByExampleAndPage(page,null);
        return list;
    }

    public List<User> selectList(){
        List<User> list = userMapper.selectByExample(null);
        return list;
    }

}
