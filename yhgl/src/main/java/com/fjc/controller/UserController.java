package com.fjc.controller;


import com.fjc.common.response.ResultJSON;
import com.fjc.entity.User;
import com.fjc.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 范小城
 * @since 2021-03-23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/login")
    @ApiOperation("登录方法")
    public String login(){
        return "ok";
    }

    /*@GetMapping("/getUsers")
    @ApiOperation("获取所有用户信息")
    public ResultJSON getUsers(@RequestParam(value = "cur",required = false,defaultValue = "1")Integer cur,
                               @RequestParam(value = "size",required = false,defaultValue = "5")Integer size){
        System.out.println(cur+"是当前页面，页面的记录数："+size);
        return ResultJSON.ok(userService.getUsers(cur, size));
    }*/

    @GetMapping("/getUsersBy")
    @ApiOperation("根据条件查询用户信息")
    public ResultJSON getUsersBy(@RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer cur,
                                 @RequestParam(value = "pageSize",required = false)Integer size,
                                 @RequestParam(value = "query",required = false)String val){
        System.out.println("val的值："+val);
        return ResultJSON.ok(userService.getUsersBy(cur, size, val));
    }

    @RequestMapping("/delOne")
    @ApiOperation("根据id删除用户")
    public String delOne(Integer id){
        int num= userService.delOne(id);
        return num> 0 ? "ok": "error";
    }

    @GetMapping("/findOne")
    @ApiOperation("根据id获取用户信息")
    public ResultJSON findOne(Integer id){
        User user=userService.findOne(id);
        return ResultJSON.ok(user);
    }

    @PostMapping(value = "/updateOne")
    @ApiOperation("更新用户信息")
    public String updateOne(@RequestBody User userForm){
        System.out.println("======="+userForm.toString());
        int num= userService.updateOne(userForm,userForm.getId());
        return num> 0 ? "ok": "error";
    }

    @GetMapping("/addOne")//未完待续...
    @ApiOperation("添加新用户")
    public String addOne(){
        User user=new User();

        int num= userService.addOne(user);
        return num> 0 ? "ok": "error";
    }
}
