package ua.tasks.den4uk.controller.usercontroller;

import ua.tasks.den4uk.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {

    private UserService userService = new UserService();

    private static final String USER_ID = "userId";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter(USER_ID));

        userService.delete(userId);

        req.setAttribute("users", userService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(req, resp);
    }

}
