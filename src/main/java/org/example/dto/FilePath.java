package org.example.dto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FilePath {
    private String inputPath;
    private String outputPath;
}
