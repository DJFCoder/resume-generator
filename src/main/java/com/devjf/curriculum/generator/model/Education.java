package com.devjf.curriculum.generator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Education {
    private String institution;
    private String course;
    private String period;
    private String type;
}
