package ua.tasks.den4uk.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.tasks.den4uk.model.User;

import java.io.FileReader;
import java.io.IOException;

public class YamlDeserializer implements Deserializer {

    private static final Logger logger = LogManager.getLogger(YamlDeserializer.class);

    @Override
    public User deserialize(String path) {
        ObjectMapper yamlMapper = new YAMLMapper();
        try {
            return yamlMapper.readValue(new FileReader(path), User.class);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }

        return null;
    }

}
