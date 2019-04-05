package ua.tasks.den4uk.controller;

import org.apache.commons.io.IOUtils;
import ua.tasks.den4uk.model.User;
import ua.tasks.den4uk.serializer.YamlSerializer;
import ua.tasks.den4uk.service.UserService;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@WebServlet("/convertIntoYaml")
public class ConvertIntoYamlServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> users = userService.getAll();

        req.setAttribute("users", users);

        File file = new File(File.separator + "users.yaml");

        YamlSerializer yamlSerializer = new YamlSerializer();
        yamlSerializer.serialize(file.getAbsolutePath(), users);

        resp.addHeader("Content-Disposition", "attachment; fileName=" + file.getName());

        ServletOutputStream outputStream = resp.getOutputStream();
        FileInputStream inputStream = new FileInputStream(file);

        IOUtils.copy(inputStream, outputStream);

        outputStream.close();
        file.deleteOnExit();
    }

}
