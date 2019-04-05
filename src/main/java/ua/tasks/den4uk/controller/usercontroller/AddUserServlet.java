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

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {

    private UserService userService = new UserService();

    private static final String FIRST_NAME = "firstName";

    private static final String LAST_NAME = "lastName";

    private static final String BIRTHDAY = "birthday";

    private static final String EMAIL = "email";

    private static final String PHONE_NUMBER = "phoneNumber";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter(FIRST_NAME);
        String lastName = req.getParameter(LAST_NAME);
        LocalDate birthday = LocalDate.parse(req.getParameter(BIRTHDAY));
        String email = req.getParameter(EMAIL);
        String phoneNumber = req.getParameter(PHONE_NUMBER);

        User user = new User(firstName, lastName, java.sql.Date.valueOf(birthday), email, phoneNumber);

        userService.create(user);

        req.setAttribute("users", userService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(req, resp);
    }

}
