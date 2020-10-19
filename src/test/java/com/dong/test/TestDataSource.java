package com.dong.test;

import com.dong.sys.domain.SysUser;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author:dm
 * @Date:2020/10/18 21:16
 * @Description:
 */
public class TestDataSource {

    public static void main(String[] args) {
        String driverName="com.mysql.cj.jdbc.Driver";//这是要连接的数据库加载器
        String dbURL="jdbc:mysql://localhost:3306/carrent2020?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";//这是要连接的端口号以及数据库名称
        String userName="root";//用户名
        String userpwd="123456";//用户密码
        try {
            Class.forName(driverName);
            System.out.println("加载驱动成功");
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("加载驱动失败");
        }
        try {
            Connection dbConn=DriverManager.getConnection(dbURL,userName,userpwd);
            System.out.println("连接数据库成功");
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }
}
