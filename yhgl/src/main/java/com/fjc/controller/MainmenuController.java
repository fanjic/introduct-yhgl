package com.fjc.controller;


import com.fjc.common.response.ResultJSON;
import com.fjc.entity.Mainmenu;
import com.fjc.mapper.MainmenuMapper;
import com.fjc.service.MainmenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 范小城
 * @since 2021-03-23
 */
@RestController
@RequestMapping("/menu")
public class MainmenuController {

    @Resource
    MainmenuService menuService;

    @GetMapping("/getMenus")
    @ApiOperation("获取所有菜单作为导航栏")
    public ResultJSON getMenus(){
        return ResultJSON.ok(menuService.getMenus());
    }

}
