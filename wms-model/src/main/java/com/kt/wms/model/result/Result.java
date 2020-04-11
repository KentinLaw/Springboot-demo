package com.kt.wms.model.result;

import lombok.Data;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/2 16:11
 * @description Restful返回实体
 */
@Data
public class Result {

    public int resultCode;

    public String message;

    public Object data;

    public Result(int resultCode, String message, Object data){
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }
}
