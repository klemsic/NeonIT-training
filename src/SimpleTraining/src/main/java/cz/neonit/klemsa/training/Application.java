package cz.neonit.klemsa.training;

import cz.neonit.klemsa.training.dao.messageinfo.LogFileMessageInfoLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
public class Application {
private final static Properties properties = new Properties();

    /**
     *
     * @param args args
     */
    public static void main(String[] args) {
        // Load properties file.
        try (InputStream input = new FileInputStream(Application.class.getResource("config.properties").getFile())){
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Run Spring application.
        SpringApplication.run(Application.class, args);
    }


    /**
     *
     * @return Application properties.
     */
    public static Properties getProperties() {
        return properties;
    }
}
