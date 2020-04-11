package com.kt.wms.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kt.wms.model.system.Permission;

import java.util.List;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/2 15:57
 * @description
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> listByUser(Integer id);
}
