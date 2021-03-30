package com.fjc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fjc.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 范小城
 * @since 2021-03-23
 */
public interface UserService extends IService<User> {

    public HashMap getUsers(Integer cur, Integer size);

    public HashMap getUsersBy(Integer cur, Integer size,String val);

    public int delOne(Integer id);

    public int addOne(User user);

    public User findOne(Integer id);

    public int updateOne(User user,Integer id);

}
