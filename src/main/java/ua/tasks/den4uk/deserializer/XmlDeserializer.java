package ua.tasks.den4uk.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.tasks.den4uk.model.User;

import java.io.FileReader;
import java.io.IOException;

public class XmlDeserializer implements Deserializer {

    private static final Logger logger = LogManager.getLogger(XmlDeserializer.class);

    @Override
    public User deserialize(String path) {
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(new FileReader(path), User.class);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }

        return null;
    }

}
