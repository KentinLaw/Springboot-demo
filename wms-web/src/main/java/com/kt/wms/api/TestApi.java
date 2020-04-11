package com.kt.wms.api;

import com.kt.wms.model.result.Result;
import com.kt.wms.model.result.ResultFactory;
import com.kt.wms.model.system.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/9 00:34
 * @description 测试
 */
@Api(value = "测试接口")
@RestController
@RequestMapping("/test")
public class TestApi {

    @GetMapping
    public Result getUserList(){
        return ResultFactory.buildSuccessResult("get 查询");
    }

    @ApiOperation(value = "测试接口")
    @GetMapping("/{id:\\d+}")
    public Result getUser(@PathVariable Integer id){
        return ResultFactory.buildSuccessResult("get 查询"+id);
    }

    @PostMapping
    public Result addTest(@RequestBody User user){
        return ResultFactory.buildSuccessResult("post 新建"+user.getNickname());
    }

    @DeleteMapping("/{username}")
    public Result delTest(@PathVariable String username){
        return ResultFactory.buildSuccessResult("del 删除"+username);
    }

    @PutMapping("/{id:\\d+}")
    public Result updateTest(@RequestBody User user){
        return ResultFactory.buildSuccessResult("put 修改"+user.getId());
    }
}
