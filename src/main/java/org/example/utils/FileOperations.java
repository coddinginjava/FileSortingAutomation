package org.example.utils;

import org.example.dto.FileInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileOperations {
    private final String outputPath;
    private final Utils utils;

    public FileOperations(String outputPath, Utils utils) {
        this.outputPath = outputPath;
        this.utils = utils;
    }

    public void startFileSort(Path p) {
        Path result = null;
        String output;
        FileInfo fileInfo = null;
        try {
            fileInfo = utils.getFileInfo(p);

            output = new StringBuffer(outputPath).append("/")
                    .append(fileInfo.getFileType()).append("/")
                    .append(fileInfo.getYear()).append("/")
                    .append(fileInfo.getMonth()).append("/").toString();

            File f = new File(output);

            boolean mkdirs = f.mkdirs();


            result = Files.move(p, Paths.get(output + fileInfo.getFileName()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Exception while moving file: " + fileInfo.getFileName());
            System.out.println("with exception : " + e.getMessage());
        }

        if (result != null) {
//     System.out.println("File  moved successfully.");
        } else {
            System.out.println("File movement failed. : " + fileInfo.getFileName());
        }

    }
}
