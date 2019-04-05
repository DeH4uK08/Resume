package ua.tasks.den4uk.deserializer;

import ua.tasks.den4uk.model.User;

public interface Deserializer {

    User deserialize(String path);

}
