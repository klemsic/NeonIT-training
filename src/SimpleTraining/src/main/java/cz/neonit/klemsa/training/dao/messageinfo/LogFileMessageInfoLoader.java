package cz.neonit.klemsa.training.dao.messageinfo;

import cz.neonit.klemsa.training.Application;
import cz.neonit.klemsa.training.domain.message.MessageInfo;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author tomasklemsa
 */
public final class LogFileMessageInfoLoader implements MessageInfoLoader {
    private static final String FILE_NAME_PATTERN = "MCP_{0}.json";
    private static final String DATE_FORMAT_PATTERN = "yyyyMMdd";
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
    public List<MessageInfo> getMessagesInfo(LocalDate date) {
        List<MessageInfo> result = new ArrayList<>();
        String fileName = "";

        Path path = Paths.get(logUrl,fileName);
        File file = path.toFile();






        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<LocalDate> getDates() {
        return null;
    }


}
