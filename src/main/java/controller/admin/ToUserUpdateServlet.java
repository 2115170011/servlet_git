package controller.admin;

import dao.UserDao;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ToUserUpdateServlet",urlPatterns = "/toUserUpdateServlet")
public class ToUserUpdateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String  id = req.getParameter("userid");

        //将这个id传给userdao的一个方法updateUserById(String userid)
        UserDao dao=new UserDao();
        User user = dao.updateUserById(id);
        //将user对象绑定到HttpServletRequest对象，转发到用户信息修改页面
        req.setAttribute("u",user);
        req.getRequestDispatcher("/resources/admin/userupdate.jsp").forward(req,resp);




    }
}
