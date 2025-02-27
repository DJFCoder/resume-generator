package com.devjf.curriculum.generator.service;

import java.util.HashMap;
import java.util.Map;

public class ResumeLanguage {
    private final Map<String, Map<String, String>> titles;

    public ResumeLanguage() {
        titles = new HashMap<>();
        initializePortuguese();
        initializeEnglish();
    }

    private void initializePortuguese() {
        Map<String, String> pt = new HashMap<>();
        pt.put("professional_summary", "Resumo Profissional");
        pt.put("technical_skills", "Competências Técnicas");
        pt.put("professional_experience", "Experiência Profissional");
        pt.put("education", "Formação Acadêmica");
        pt.put("projects", "Projetos");
        pt.put("technologies", "Tecnologias: ");
        pt.put("certifications", "Certificações");
        pt.put("languages", "Idiomas");
        titles.put("Portuguese", pt);
    }

    private void initializeEnglish() {
        Map<String, String> en = new HashMap<>();
        en.put("professional_summary", "Professional Summary");
        en.put("technical_skills", "Technical Skills");
        en.put("professional_experience", "Professional Experience");
        en.put("education", "Education");
        en.put("projects", "Projects");
        en.put("technologies", "Technologies: ");
        en.put("certifications", "Certifications");
        en.put("languages", "Languages");
        titles.put("English", en);
    }

    public String getTitle(String language, String key) {
        return titles.getOrDefault(language, titles.get("Portuguese"))
                    .getOrDefault(key, "");
    }
}