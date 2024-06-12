package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {

    private String year;
    private String month;
    private String fileType;
    private String fileName;
    private String filePath;

}
