package ua.tasks.den4uk.serializer;

import ua.tasks.den4uk.model.User;

import java.util.List;

public interface Serializer {

    void serialize(String path, List<User> users);

}
