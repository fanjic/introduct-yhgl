package com.fjc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fjc.entity.Mainmenu;
import com.fjc.mapper.MainmenuMapper;
import com.fjc.mapper.SubmenuMapper;
import com.fjc.service.MainmenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 范小城
 * @since 2021-03-23
 */
@Service
public class MainmenuServiceImpl extends ServiceImpl<MainmenuMapper, Mainmenu> implements MainmenuService {

    @Resource
    MainmenuMapper mainmenuMapper;
    @Resource
    SubmenuMapper submenuMapper;


    @Override
    public List getMenus() {
        List<Mainmenu> model;
        QueryWrapper mainWrapper= new QueryWrapper();
        mainWrapper.select("id","title","path");
        QueryWrapper subWrapper= new QueryWrapper();
        model=mainmenuMapper.selectList(mainWrapper);
        for(Mainmenu mainmenu : model){
            subWrapper.clear();
            subWrapper.select("id","title","path");
            subWrapper.eq("mainid",mainmenu.getId());
            mainmenu.setSubmenus(submenuMapper.selectList(subWrapper));
            }
        return model;
    }
}
