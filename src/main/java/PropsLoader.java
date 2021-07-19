import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropsLoader {
    private static final Logger log = LoggerFactory.getLogger(PropsLoader.class);

    public Properties loadProperties(){
        Properties props = new Properties();
        String path = Path.PROPERTIES_PATH.getProperty();

        log.info("Try to load properties from: {}", path);

        try(FileInputStream fileInputStream = new FileInputStream(path)){
            props.load(fileInputStream);

        } catch (FileNotFoundException exception) {
            log.error("FileNotFoundException in read properties method from file: {}", path);
            throw new RuntimeException(exception);

        } catch (IOException exception) {
            log.error("IOException in read properties method from file: {}", path);
            throw new RuntimeException(exception);
        }

        log.info("Properties from {} was read successfully", path);
        return props;
    }
}