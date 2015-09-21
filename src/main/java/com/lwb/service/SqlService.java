package com.lwb.service;

import org.springframework.stereotype.Component;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.SortedMap;
/**
 * Created by lwb on 2014/10/31.
 */
@Component
public class SqlService {
    private String driver="com.mysql.jdbc.Driver";
    private String url="jdbc:mysql://localhost:3306/jobtest";
    private String username="root";
    private String password ="root";

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public Connection getConnection(){
        try {
            Class.forName(driver);
            conn= DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public void close(){
        try {
            if (rs!=null){
                rs.close();
            }
            if (ps!=null){
                ps.close();
            }
            if (conn!=null){
                conn.close();
            }

        }catch (Exception e){}
    }

    public SortedMap[] query(String sql,Object[] params){
        SortedMap[] sms=null;
        getConnection();
        try {
            ps=conn.prepareStatement(sql);
            if (params!=null){
                for (int i=0;i<params.length;i++){
                    ps.setObject(i+1,params[i]);
                }
            }
            rs=ps.executeQuery();
            Result result = ResultSupport.toResult(rs);
            sms = result.getRows();



        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }
        return sms;
    }

    public void update(String sql,Object[] params){
        getConnection();
        try {
            ps=conn.prepareStatement(sql);
            if (params!=null){
                for (int i=0;i<params.length;i++){
                    ps.setObject(i+1,params[i]);
                }
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }

    }

    public void print(Object o){
        Class c = o.getClass();
//        Field[] fields = c.getDeclaredFields();
//        for (Field field:fields){
//            System.out.println(field.getName());
//        }
        Method[] methods = c.getDeclaredMethods();
        for (Method method:methods){
            if (method.getName().startsWith("get")){
                try {
                    System.out.println( method.invoke(o));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Object convertSortedMapToObject(SortedMap sm,Object o){
        Class c = o.getClass();
        Method[] methods = c.getDeclaredMethods();
        //1、为什么不用这种方式来得到属性名称
        //new Field().getName();
        for (Method method:methods){
            if (method.getName().startsWith("set")){
                String fieldName = method.getName().substring(3).toLowerCase();
                try {
                    method.invoke(o,sm.get(fieldName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return o;
    }

}
