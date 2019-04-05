package ua.tasks.den4uk.controller;

import ua.tasks.den4uk.model.User;
import ua.tasks.den4uk.service.PDFCreator;
import ua.tasks.den4uk.service.UserService;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@WebServlet("/exportToPdf")
public class ExportToPdfServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = userService.getById(Integer.parseInt(req.getParameter("userId")));

        resp.setContentType("application/pdf;charset=UTF-8");
        resp.addHeader("Content-Disposition", "inline; fileName=" + user.getLastName() + ".pdf");

        ServletOutputStream outputStream = resp.getOutputStream();
        ByteArrayOutputStream out = PDFCreator.getPDF(user);
        out.writeTo(outputStream);

        outputStream.close();
    }

}
