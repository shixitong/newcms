package com.quick4j.web.service.impl;

import java.util.List;
import javax.annotation.Resource;

import com.quick4j.web.dao.PermissionMapper;
import com.quick4j.web.model.Permission;
import com.quick4j.web.service.PermissionService;
import org.springframework.stereotype.Service;
import com.quick4j.core.generic.GenericDao;
import com.quick4j.core.generic.GenericServiceImpl;

/**
 * 权限Service实现类
 *
 * @author StarZou
 * @since 2014年6月10日 下午12:05:03
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
