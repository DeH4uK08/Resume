package ua.tasks.den4uk.controller.usercontroller;

import ua.tasks.den4uk.model.User;
import ua.tasks.den4uk.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/infoAboutUser")
public class InfoAboutUserServlet extends HttpServlet {

    private UserService userService = new UserService();

    private static final String USER_ID = "userId";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getById(Integer.parseInt(req.getParameter(USER_ID)));

        req.setAttribute("user", user);
        req.setAttribute("jobs", user.getJobs());
        req.getRequestDispatcher("/WEB-INF/views/infoAboutUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(req, resp);
    }

}
