package com.fjc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fjc.entity.User;
import com.fjc.mapper.UserMapper;
import com.fjc.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public HashMap<String, Object> getUsers(Integer cur, Integer size) {
        Page page=new Page(cur,size);
        IPage<User> iPage = userMapper.selectPage(page,new QueryWrapper());
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("users",iPage.getRecords());
        model.put("total",iPage.getTotal());
        model.put("current",iPage.getCurrent());
        model.put("pages",iPage.getPages());
        return model;
    }

    @Override
    public HashMap<String, Object> getUsersBy(Integer cur, Integer size, String val) {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.like("name",val).or().like("skill",val);
        Page page=new Page(cur,size);
        IPage<User> iPage = userMapper.selectPage(page,wrapper);
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("users",iPage.getRecords());
        model.put("total",iPage.getTotal());
        model.put("current",iPage.getCurrent());
        model.put("pages",iPage.getPages());
        return model;
    }

    @Override
    public int delOne(Integer id) {
       return userMapper.deleteById(id);
    }

    @Override
    public int addOne(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User findOne(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public int updateOne(User user,Integer id) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",id);
        return userMapper.update(user,wrapper);
    }
}
