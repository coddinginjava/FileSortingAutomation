package org.example.utils;

import org.example.dto.FileExtensions;
import org.example.dto.FileInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

public class Utils {

    private final Set<String> videoSet = new HashSet<>();
    private final Set<String> imageSet = new HashSet<>();
    private final Set<String> audioSet = new HashSet<>();
    private final Set<String> compressedSet = new HashSet<>();
    private final Set<String> documentsSet = new HashSet<>();
    private final Set<String> executablesSet = new HashSet<>();


    public Utils(FileExtensions fileExtensions) {

        this.videoSet.addAll(fileExtensions.getExtension().get("video"));
        this.imageSet.addAll(fileExtensions.getExtension().get("image"));
        this.audioSet.addAll(fileExtensions.getExtension().get("audio"));
        this.compressedSet.addAll(fileExtensions.getExtension().get("compressed"));
        this.documentsSet.addAll(fileExtensions.getExtension().get("documents"));
        this.executablesSet.addAll(fileExtensions.getExtension().get("executables"));
    }


    public FileInfo getFileInfo(Path path) throws IOException {
        FileInfo fileInfo = new FileInfo();
        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
        FileTime fileTime = attr.lastModifiedTime();
        TemporalAccessor ta = DateTimeFormatter.ISO_INSTANT.parse(fileTime.toString());
        Instant i = Instant.from(ta);
        Date d = Date.from(i);

        fileInfo.setFileName(path.getFileName().toString());
        fileInfo.setFileType(getFileType(path.getFileName().toString()));
        fileInfo.setFilePath(path.toString());
        fileInfo.setYear((d.getYear() + 1900) + "");
        fileInfo.setMonth(d.getMonth() + 1 + "");


        return fileInfo;
    }

    private String getFileType(String filename) {
        String fileExtensionType = filename.split("\\.")[1];


        if (videoSet.contains(fileExtensionType)) {
            return "Video";
        } else if (imageSet.contains(fileExtensionType)) {
            return "Image";
        } else if (audioSet.contains(fileExtensionType)) {
            return "Audio";
        } else if (compressedSet.contains(fileExtensionType)) {
            return "Compressed";
        } else if (documentsSet.contains(fileExtensionType)) {
            return "documents";
        } else if (executablesSet.contains(fileExtensionType)) {
            return "windowsExecutables";
        } else {
            return "general";
        }
    }

}
