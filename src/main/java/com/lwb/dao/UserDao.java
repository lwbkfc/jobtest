package com.lwb.dao;

import com.lwb.service.SqlService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.SortedMap;

/**
 * Created by lwb on 2015/9/19.
 */
@Component
public class UserDao {
    public void insert(String sql,Object[] params){
        SqlService sqlService = new SqlService();
        sqlService.update(sql,params);
    }

    public void deleteAll(String sql,Object[] params){
        SqlService sqlService = new SqlService();
        sqlService.update(sql,params);
    }

    public SortedMap[] getUserList(String sql,Object[] params){
        SqlService sqlService = new SqlService();
        return sqlService.query(sql,params);
    }
}
