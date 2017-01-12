package com.newcms.web.service.impl;

import java.util.List;
import javax.annotation.Resource;

import com.newcms.web.service.RoleService;
import org.springframework.stereotype.Service;
import com.newcms.core.generic.GenericDao;
import com.newcms.core.generic.GenericServiceImpl;
import com.newcms.web.dao.RoleMapper;
import com.newcms.web.model.Role;

/**
 * 角色Service实现类
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
