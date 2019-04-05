package ua.tasks.den4uk.deserializer;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.tasks.den4uk.model.User;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonDeserializer implements Deserializer {

    private static final Logger logger = LogManager.getLogger(JsonDeserializer.class);

    @Override
    public User deserialize(String path) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(new FileReader(path), User.class);
        } catch (FileNotFoundException ex) {
            logger.error(ex.getMessage());
        }

        return null;
    }

}
