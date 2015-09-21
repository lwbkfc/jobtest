package com.lwb.service;

import com.lwb.dao.UserDao;
import com.lwb.po.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.SortedMap;
/**
 * Created by lwb on 2015/9/19.
 */
@Component
public class UserService {
    @Resource
    private UserDao userDao;
    public void insertUser(User user) {
        String sql = "insert into user (username,password,status,startTime,endTime) values(?,?,?,?,?)";
        Object[] params = {user.getUsername(),user.getPassword(),user.getStatus(),user.getStartTime(),user.getEndTime()};
        userDao.insert(sql,params);
    }

    public void deleteAll(){
        String sql = "delete from user";
        userDao.deleteAll(sql, null);
    }

    public SortedMap[] getUserList(){
        String sql = "select * from user";
        return userDao.getUserList(sql,null);
    }
}
