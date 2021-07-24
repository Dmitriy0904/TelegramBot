package props;

import java.util.Properties;

public interface PropertyLoader {
    Properties loadProperties(String path);
}