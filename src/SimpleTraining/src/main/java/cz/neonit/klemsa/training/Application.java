package cz.neonit.klemsa.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
public class Application {
private final static Properties properties = new Properties();

    static {
        // Load properties file.
        try (InputStream input = new FileInputStream(Application.class.getClassLoader().getResource("config.properties").getFile())){
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param args args
     */
    public static void main(String[] args) {


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
