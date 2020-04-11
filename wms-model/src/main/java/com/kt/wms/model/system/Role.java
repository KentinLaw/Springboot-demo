package com.kt.wms.model.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/2 16:11
 * @description 角色类
 */
@Data
@TableName("sys_role")
@NoArgsConstructor
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = AUTO)
    private Integer id;

    private String roleCode;

    private String roleName;

    private String description;
}
