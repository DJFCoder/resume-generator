package com.devjf.curriculum.generator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    private String name;
    private String description;
    private String technologiesUsed;
}
