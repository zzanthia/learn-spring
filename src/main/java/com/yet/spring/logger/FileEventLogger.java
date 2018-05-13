package com.yet.spring.logger;

import com.yet.spring.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FileEventLogger implements EventLogger {

    private File file;

    public FileEventLogger(String fileName) {
        file = new File(fileName);
    }

    public void init() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new IllegalStateException("Cannot create new file");
            }
        }
        if (!file.canWrite()) {
            throw new IllegalStateException("Cannot write to file");
        }
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeLines(file, Arrays.asList(event), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
