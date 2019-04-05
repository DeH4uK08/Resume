package ua.tasks.den4uk.controller.usercontroller;

import ua.tasks.den4uk.model.User;
import ua.tasks.den4uk.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {

    private UserService userService = new UserService();

    private static final String USER_ID = "userId";

    private static final String FIRST_NAME = "firstName";

    private static final String LAST_NAME = "lastName";

    private static final String BIRTHDAY = "birthday";

    private static final String EMAIL = "email";

    private static final String PHONE_NUMBER = "phoneNumber";

    private int userId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userId = Integer.parseInt(req.getParameter(USER_ID));

        req.setAttribute("user", userService.getById(userId));
        req.getRequestDispatcher("/WEB-INF/views/updateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();

        user.setUserId(userId);
        user.setFirstName(req.getParameter(FIRST_NAME));
        user.setLastName(req.getParameter(LAST_NAME));
        user.setBirthday(java.sql.Date.valueOf(LocalDate.parse(req.getParameter(BIRTHDAY))));
        user.setEmail(req.getParameter(EMAIL));
        user.setPhoneNumber(req.getParameter(PHONE_NUMBER));

        userService.update(user);

        req.setAttribute("users", userService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(req, resp);
    }

}
