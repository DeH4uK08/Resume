package ua.tasks.den4uk.controller.jobcontroller;

import ua.tasks.den4uk.model.User;
import ua.tasks.den4uk.service.JobService;
import ua.tasks.den4uk.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteJob")
public class DeleteJobServlet extends HttpServlet {

    private JobService jobService = new JobService();

    private UserService userService = new UserService();

    private static final String JOB_ID = "jobId";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int jobId = Integer.parseInt(req.getParameter(JOB_ID));

        User user = userService.getById(jobService.getById(jobId).getUserId());

        jobService.delete(jobId);

        req.setAttribute("user", user);
        resp.sendRedirect("updateUser?userId=" + user.getUserId());
    }

}
