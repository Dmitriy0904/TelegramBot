package props;

import config.PathConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsLoader {
    private static final Logger log = LoggerFactory.getLogger(PropsLoader.class);

    public Properties loadProperties(String PATH){
        Properties props = new Properties();

        log.info("Try to load properties from: {}", PATH);

        try(FileInputStream fileInputStream = new FileInputStream(PATH)){
            props.load(fileInputStream);

        } catch (FileNotFoundException exception) {
            log.error("FileNotFoundException in read properties method from file: {}", PATH);
            throw new RuntimeException(exception);

        } catch (IOException exception) {
            log.error("IOException in read properties method from file: {}", PATH);
            throw new RuntimeException(exception);
        }

        log.info("props.BotProps from {} was read successfully", PATH);
        return props;
    }
}