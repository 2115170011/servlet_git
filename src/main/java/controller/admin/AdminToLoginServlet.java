package controller.admin;

import dao.UserDao;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="AdminToLoginServlet",urlPatterns = "/loginServlet")
public class AdminToLoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //中文乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        //接收参数
        String password = req.getParameter("password");
        //根据账号，查询信息
        UserDao userDao=new UserDao();
        User user = userDao.selectAdminInfo(username);
        if(user.getUserName().equals(username)&&user.getUserPassword().equals(password)){
            //匹配成功，跳转到主页
            //绑定用户到session中
            HttpSession session = req.getSession();
            session.setAttribute("loginName",username);
            //将session绑定到
            //跳转到主页转发
            req.getRequestDispatcher("/resources/admin/AdminTemp.jsp").forward(req,resp);
            

        }else{
            //错误
            req.setAttribute("error","用户名或密码错误！");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }



    }
}
