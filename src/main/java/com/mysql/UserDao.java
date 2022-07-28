package com.mysql;

import java.sql.SQLException;
import java.util.*;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

public class UserDao {

    public List<User> getUserAll() throws SQLException {
        List<User> list = new ArrayList<User>();
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());

        //登录用
        String sql = "select username,name from  user where username = ? and password = ?";

        //得到全部用户
        String sql02 = "select ename,job from emp";


        return qr.query(sql02, new BeanListHandler<User>(User.class));
    }

    @Test
    public void test01() {
        System.out.println("----------------------");
        try {
            List<User> list = getUserAll();
            Iterator it = list.iterator();

            for(int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).getEname() + "," + list.get(i).getJob());
            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
