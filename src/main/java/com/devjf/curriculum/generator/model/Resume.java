package com.devjf.curriculum.generator.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Resume {
    private String name;
    private String email;
    private String phone;
    private String linkedin;
    private String github;
    private String professionalSummary;
    private List<String> technicalSkills;
    private List<Experience> experiences;
    private List<Education> education;
    private List<Project> projects;
    private List<String> certifications;
    private List<Language> languages;
    
    public Resume() {
        this.technicalSkills = new ArrayList<>();
        this.experiences = new ArrayList<>();
        this.education = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.certifications = new ArrayList<>();
        this.languages = new ArrayList<>();
    }
}
