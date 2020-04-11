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
 * @description 访问资源类
 */
@Data
@TableName("sys_user")
@NoArgsConstructor
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = AUTO)
    private int id;

    private String url;

    private String method;

    private String permissionName;

    private Integer parentId;

}
