package dao;

import pojo.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public List<User> userSelect(String id) throws SQLException {
        List<User> list = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        String sql = "";

        if (id == null || id.equals("")) {

            //执行
            sql = "select * from tb_user";
        } else {
            sql = "select * from tb_user where id=" + id;
        }
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet r = ps.executeQuery();
        //遍历结果集
        while (r.next()) {
            Integer id1 = r.getInt("ID");
            String userName = r.getString("USER_NAME");
            String pwd = r.getString("USER_PASSWORD");
            Integer userType = r.getInt("USER_TYPE");
            Integer userState = r.getInt("USER_STATE");
            User user = new User();
            user.setId(id1);
            user.setUserName(userName);
            user.setUserPassword(pwd);
            user.setUserType(userType);
            user.setUserState(userState);
            list.add(user);

        }
        return list;
    }


    //根据一个方法根据管理员名字查询信息
    public User selectAdminInfo(String name) {

        Connection connection = DBUtil.getConn();
        User user = null;
        String sql = "select * from tb_user where user_name=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet r = ps.executeQuery();

            if (r.next()) {
                Integer id = r.getInt("ID");
                String userName = r.getString("USER_NAME");
                String pwd = r.getString("USER_PASSWORD");
                Integer userType = r.getInt("USER_TYPE");
                Integer userState = r.getInt("USER_STATE");
                user = new User();
                user.setId(id);
                user.setUserName(userName);
                user.setUserPassword(pwd);
                user.setUserType(userType);
                user.setUserState(userState);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }


    public int deleteUserById(String id) {
        int row = 0;
        //获取连接
        Connection connection = DBUtil.getConn();
        //sql
        String sql = "delete from tb_user where id=?";
        //执行sql语句的对象
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            //给问号赋值
            ps.setInt(1, Integer.parseInt(id));
            //执行
            row = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row;

    }


    public User updateUserById(String id){
        User user=null;
        Connection conn=DBUtil.getConn();
        String sql="select * from tb_user where id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(id));
            ResultSet r = ps.executeQuery();
            if(r.next()){
                Integer id1 = r.getInt("ID");
                String userName = r.getString("USER_NAME");
                String pwd = r.getString("USER_PASSWORD");
                Integer userType = r.getInt("USER_TYPE");
                Integer userState = r.getInt("USER_STATE");
                user = new User();
                user.setId(id1);
                user.setUserName(userName);
                user.setUserPassword(pwd);
                user.setUserType(userType);
                user.setUserState(userState);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;


    }


    public int userUpdateById(User user){
        int row=0;
        Connection conn=DBUtil.getConn();
        String sql="update tb_user set user_password=? where id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getUserPassword());
            ps.setInt(2,user.getId());
            row=ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row;

    }


}



