package org.example.config;

import com.google.gson.Gson;
import org.example.dto.FileExtensions;
import org.example.dto.FilePath;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Configuration {
    static Gson gson = new Gson();
    static File file = new File("src/main/resources/ConfigJson.json");

    private static final String FILE_EXTENSION_KEY_IN_JSON = "FileExtensions";
    private static final String PATHS = "path";
    private static final String INPUT_PATH = "inputSourcePath";
    private static final String OUTPUT_PATH = "outputDestinationPath";


    public static FileExtensions getFileExtension() throws Exception {
        FileExtensions fe = new FileExtensions();
        fe.setExtension((Map<String, Set<String>>) getDataFromConfigMap(FILE_EXTENSION_KEY_IN_JSON));
        return fe;
    }

    public static FilePath getFilePath() throws Exception {
        Map<String, String> map = (Map<String, String>) getDataFromConfigMap(PATHS);
        return new FilePath(map.get(INPUT_PATH), map.get(OUTPUT_PATH));
    }

    private static Object getDataFromConfigMap(String jsonKey) throws Exception {
        for (Map.Entry<?, ?> entry : getConfigMap().entrySet()) {
            if (entry.getKey().equals(jsonKey)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private static Map<? extends String, ?> getConfigMap() throws Exception {
        Reader reader = Files.newBufferedReader(file.toPath());
        Map<? extends String, ?> map = gson.fromJson(reader, Map.class);
        reader.close();
        return map;
    }


}
