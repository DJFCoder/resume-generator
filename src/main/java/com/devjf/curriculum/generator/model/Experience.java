package com.devjf.curriculum.generator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Experience {
    private String company;
    private String position;
    private String period;
    private String description;
}
