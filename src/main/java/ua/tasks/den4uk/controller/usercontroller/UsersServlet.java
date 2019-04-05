package ua.tasks.den4uk.controller.usercontroller;

import ua.tasks.den4uk.model.User;
import ua.tasks.den4uk.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/users", ""})
public class UsersServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.getAll();

        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(req, resp);
    }

}
