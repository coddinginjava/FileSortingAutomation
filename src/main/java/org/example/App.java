package org.example;

import org.example.config.Configuration;
import org.example.utils.FileOperations;
import org.example.utils.Utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        long StartTime = System.currentTimeMillis();

        final String outputPath = Configuration.getFilePath().getOutputPath();
        final String inputPath = Configuration.getFilePath().getInputPath();

        List<Path> pathList = null;

        try (Stream<Path> paths = Files.walk(Paths.get(inputPath))) {
            pathList = paths.filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }

        Utils utils = new Utils(Configuration.getFileExtension());

        FileOperations fileOperations = new FileOperations(outputPath,utils);

        pathList.parallelStream()
                .forEach(fileOperations::startFileSort);


        System.out.println("time taken in sec : " + (System.currentTimeMillis() - StartTime) / 1000);
    }

}


