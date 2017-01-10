package com.quick4j.web.service.impl;

import java.util.List;
import javax.annotation.Resource;

import com.quick4j.web.service.RoleService;
import org.springframework.stereotype.Service;
import com.quick4j.core.generic.GenericDao;
import com.quick4j.core.generic.GenericServiceImpl;
import com.quick4j.web.dao.RoleMapper;
import com.quick4j.web.model.Role;

/**
 * 角色Service实现类
 *
 * @author StarZou
 * @since 2014年6月10日 下午4:16:33
 */
@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, Long> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public GenericDao<Role, Long> getDao() {
        return roleMapper;
    }

    @Override
    public List<Role> selectRolesByUserId(Long userId) {
        return roleMapper.selectRolesByUserId(userId);
    }

}
