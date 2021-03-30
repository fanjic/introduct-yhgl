package com.fjc.service;

import com.fjc.entity.Mainmenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 范小城
 * @since 2021-03-23
 */
public interface MainmenuService extends IService<Mainmenu> {

    List getMenus();
}
