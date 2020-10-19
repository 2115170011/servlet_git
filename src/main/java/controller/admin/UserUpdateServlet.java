package controller.admin;

import dao.UserDao;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="UserUpdateServlet",urlPatterns = "/userUpdateServlet")

public class UserUpdateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userid = req.getParameter("userid");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //将参数封装到user对象中
        User user=new User();
        user.setId(Integer.parseInt(userid));
        user.setUserName(username);
        user.setUserPassword(password);
        //将封装好的参数对象传入userdao的userUpdateById(User user)
        UserDao userDao=new UserDao();
        int row = userDao.userUpdateById(user);
        if(row>0){
            req.getRequestDispatcher("/resources/admin/default.jsp").forward(req,resp);

        }else{
            req.getRequestDispatcher("error.jsp").forward(req,resp);


        }

    }
}
