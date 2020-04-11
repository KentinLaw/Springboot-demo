package com.kt.wms.util;

import com.alibaba.fastjson.JSON;
import com.kt.wms.model.result.Result;
import lombok.Cleanup;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/2 16:11
 * @description 响应工具类
 */
public class ResponseUtils {
    public static void write(HttpServletResponse response, Result result){
        try {
            response.setContentType("text/html;charset=utf-8");
            @Cleanup PrintWriter out=response.getWriter();
            out.print(JSON.toJSON(result));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
