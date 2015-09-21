package com.lwb.test;

import com.lwb.po.User;
import com.lwb.po.UserEnum;
import com.lwb.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.SortedMap;

/**
 * Created by lwb on 2015/9/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-core.xml" })
public class TestUser {
    @Resource
    private UserService userService;
    @Test
    public void testInsert(){
        long now = new Date().getTime()/1000*1000;
        for (int i=1;i<=10;i++){
            User user = new User();
            user.setUsername("lwb");
            user.setPassword("11111111");
            user.setStatus(UserEnum.USER_STATUS.NOT_START.toString());
            user.setStartTime(now+i*60000);
            user.setEndTime(now+(i+1)*60000);
            userService.insertUser(user);
        }
    }

    @Test
    public void testDeleteAll(){
        userService.deleteAll();
    }

    @Test
    public void testGetUserList(){
        SortedMap[] sms = userService.getUserList();
        for (SortedMap sm:sms){
            System.out.println(sm.get("id")+" "+sm.get("status")+" "+sm.get("startTime")+" "+sm.get("endTime"));
        }
    }
}
