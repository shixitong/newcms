package com.newcms.web.service.impl;

import java.util.List;
import javax.annotation.Resource;

import com.newcms.web.dao.PermissionMapper;
import com.newcms.web.model.Permission;
import com.newcms.web.service.PermissionService;
import org.springframework.stereotype.Service;
import com.newcms.core.generic.GenericDao;
import com.newcms.core.generic.GenericServiceImpl;

/**
 * 权限Service实现类
 */
@Service
public class PermissionServiceImpl extends GenericServiceImpl<Permission, Long> implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;


    @Override
    public GenericDao<Permission, Long> getDao() {
        return permissionMapper;
    }

    @Override
    public List<Permission> selectPermissionsByRoleId(Long roleId) {
        return permissionMapper.selectPermissionsByRoleId(roleId);
    }
}
