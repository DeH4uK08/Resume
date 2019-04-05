package ua.tasks.den4uk.controller.jobcontroller;

import ua.tasks.den4uk.model.Job;
import ua.tasks.den4uk.model.User;
import ua.tasks.den4uk.service.JobService;
import ua.tasks.den4uk.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/addJob")
public class AddJobServlet extends HttpServlet {

    private JobService jobService = new JobService();

    private UserService userService = new UserService();

    private static final String POSITION = "position";

    private static final String COMPANY_NAME = "companyName";

    private static final String BEGIN = "begin";

    private static final String END = "end";

    private static final String USER_ID = "userId";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String position = req.getParameter(POSITION);
        String companyName = req.getParameter(COMPANY_NAME);
        LocalDate begin = LocalDate.parse(req.getParameter(BEGIN));
        LocalDate end = LocalDate.parse(req.getParameter(END));
        int userId = Integer.parseInt(req.getParameter(USER_ID));

        Job job = new Job(position, companyName, java.sql.Date.valueOf(begin), java.sql.Date.valueOf(end), userId);

        jobService.create(job);

        User user = userService.getById(userId);
        List<Job> jobs = user.getJobs();
        jobs.add(job);
        user.setJobs(jobs);

        req.setAttribute("user", user);
        resp.sendRedirect("/updateUser?userId=" + userId);
    }

}
