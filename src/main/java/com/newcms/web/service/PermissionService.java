package com.newcms.web.service;

import java.util.List;
import com.newcms.core.generic.GenericService;
import com.newcms.web.model.Permission;

/**
 * 权限 业务接口
 **/
public interface PermissionService extends GenericService<Permission, Long> {

    /**
     * 通过角色id 查询角色 拥有的权限
     * 
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionsByRoleId(Long roleId);

}
