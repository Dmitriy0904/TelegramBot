package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class PreStartConfig {
    private static final Logger log = LoggerFactory.getLogger(PreStartConfig.class);

    public void configure(){
        String botPropsPath = PathConfig.BOT_PROPERTIES_PATH.getPath();
        File botPropsFile = new File(botPropsPath);
        if(!botPropsFile.exists()){
            log.error("BotProps file {} does not exist", botPropsPath);
            throw new RuntimeException("BotProperty file does not exist");
        }
    }
}
