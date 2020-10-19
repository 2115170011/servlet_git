package db;

import org.junit.Test;
import util.DBUtil;

import javax.servlet.annotation.WebServlet;
import java.sql.Connection;

public class testDB {
    //在方法上面使用注解测试
    @Test
    public void test01(){
        Connection connection=DBUtil.getConn();
        System.out.println(connection);

    }
}
