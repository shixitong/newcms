package com.newcms.web.service;

import java.util.List;
import com.newcms.core.generic.GenericService;
import com.newcms.web.model.Role;

/**
 * 角色 业务接口
 **/
public interface RoleService extends GenericService<Role, Long> {
    /**
     * 通过用户id 查询用户 拥有的角色
     * 
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(Long userId);
}
