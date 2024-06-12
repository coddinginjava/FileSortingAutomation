package org.example.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileExtensions {
    private Map<String, Set<String>> extension;
}
