package ua.tasks.den4uk.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.tasks.den4uk.model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class YamlSerializer implements Serializer {

    private static final Logger logger = LogManager.getLogger(YamlSerializer.class);

    @Override
    public void serialize(String path, List<User> users) {
        ObjectMapper yamlMapper = new YAMLMapper();
        try {
            yamlMapper.writeValue(new FileWriter(path), users);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

}
