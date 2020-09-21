package cz.neonit.klemsa.training.dao;

import cz.neonit.klemsa.training.Application;
import cz.neonit.klemsa.training.domain.communication.CommunicationInfo;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author tomasklemsa
 */
public final class LogFileMessageInfoLoader implements MessageInfoLoader {
    private static final String FILE_NAME_PATTERN = "MCP_%s.json";
    private final String logUrl;

    /**
     *
     */
    public LogFileMessageInfoLoader() {
        Properties properties = Application.getProperties();
        logUrl = properties.getProperty("dao.messageinfo.log.path");
    }

    /**
     *
     * @param date in witch you search for message info.
     * @return
     */
    @Override
    public List<CommunicationInfo> getMessagesInfo(String date) {
        Objects.requireNonNull(date);
        List<CommunicationInfo> result = new ArrayList<>();
        String fileName = String.format(FILE_NAME_PATTERN, date);

        Path path = Paths.get(logUrl,fileName);
        File file = path.toFile();

        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                result.add(CommunicationInfoParser.parse(line));
            }

        } catch(IOException e) {
            // Do nothing and return empty List.
        }

        return result;
    }
}
