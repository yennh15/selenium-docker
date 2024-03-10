package com.yenpractice.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class DataLoader {
    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    public static InputStream getData(String path) throws Exception{
        log.info("Reading data from location: {}", path);
        InputStream stream = DataLoader.class.getClassLoader().getResourceAsStream(path);
        if(Objects.nonNull(stream)){
            return stream;
        }
        return Files.newInputStream(Path.of(path));
    }
}
