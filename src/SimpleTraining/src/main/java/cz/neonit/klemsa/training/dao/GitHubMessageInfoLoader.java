package cz.neonit.klemsa.training.dao;

import cz.neonit.klemsa.training.Application;
import cz.neonit.klemsa.training.domain.communication.CommunicationInfo;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class GitHubMessageInfoLoader implements MessageInfoLoader {
    private static final String FILE_NAME_PATTERN = "MCP_%s.json";
    private final String logUrl;

    /**
     *
     */
    public GitHubMessageInfoLoader() {
        Properties properties = Application.getProperties();
        logUrl = properties.getProperty("dao.messageinfo.github.path");
    }

    @Override
    public List<CommunicationInfo> getMessagesInfo(String date) {
        Objects.requireNonNull(date);
         List<CommunicationInfo> result = new ArrayList<>();
        String fileName = String.format(FILE_NAME_PATTERN, date);

        try {
            URL url = new URL(logUrl + "/" + fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
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
