package com.devjf.curriculum.generator.service;

import com.devjf.curriculum.generator.model.Education;
import com.devjf.curriculum.generator.model.Experience;
import com.devjf.curriculum.generator.model.Language;
import com.devjf.curriculum.generator.model.Project;
import com.devjf.curriculum.generator.model.Resume;
import org.apache.poi.xwpf.usermodel.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class DocxGenerator implements AutoCloseable {
    private XWPFDocument document;
    private String selectedLanguage;
    private final ResumeLanguage resumeLanguage;

    public DocxGenerator() {
        this.resumeLanguage = new ResumeLanguage();
    }

    public void setLanguage(String language) {
        this.selectedLanguage = language;
    }

    private String getTitle(String key) {
        return resumeLanguage.getTitle(selectedLanguage, key);
    }

    public void generateResume(Resume resume, String outputPath) throws IOException {
        try {
            document = new XWPFDocument();
            addHeader(resume);
            addHorizontalLine();
            addSection(getTitle("professional_summary"), // Alterado para usar getTitle
                    resume.getProfessionalSummary());
            addHorizontalLine();
            addTechnicalSkills(resume);
            addHorizontalLine();
            addExperiences(resume);
            addHorizontalLine();
            addEducation(resume);
            addHorizontalLine();
            addProjects(resume);
            addHorizontalLine();
            addCertifications(resume);
            addHorizontalLine();
            addLanguages(resume);
            try (FileOutputStream out = new FileOutputStream(outputPath)) {
                document.write(out);
            }
        } finally {
            close();
        }
    }

    private void addHeader(Resume resume) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setBold(true);
        run.setFontSize(16);
        run.setText(resume.getName());
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setText(resume.getEmail() + " | " + resume.getPhone());
        run.addBreak();
        run.setText(resume.getLinkedin() + " | " + resume.getGithub());
    }

    private void addSection(String title, String content) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setBold(true);
        run.setText(title);
        if (content != null && !content.isEmpty()) {
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.setText(content);
        }
    }

    private void addHorizontalLine() {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setBorderBottom(Borders.SINGLE);
    }

    private void addTechnicalSkills(Resume resume) {
        addSection(getTitle("technical_skills"),
                String.join(", ",
                        resume.getTechnicalSkills()));
    }

    private void addExperiences(Resume resume) {
        addSection(getTitle("professional_experience"),
                null);
        for (Experience exp : resume.getExperiences()) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setBold(true);
            run.setText(exp.getCompany() + " - " + exp.getPosition());
            run.addBreak();
            run.setBold(false);
            run.setText(exp.getPeriod());
            run.addBreak();
            run.setText(exp.getDescription());
            run.addBreak();
        }
    }

    private void addEducation(Resume resume) {
        addSection(getTitle("education"),
                null);
        for (Education edu : resume.getEducation()) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setBold(true);
            run.setText(edu.getInstitution() + " - " + edu.getCourse());
            run.addBreak();
            run.setBold(false);
            run.setText(edu.getType() + " | " + edu.getPeriod());
            run.addBreak();
        }
    }

    private void addProjects(Resume resume) {
        addSection(getTitle("projects"),
                null);
        for (Project proj : resume.getProjects()) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setBold(true);
            run.setText(proj.getName());
            run.addBreak();
            run.setBold(false);
            run.setText(proj.getDescription());
            run.addBreak();
            run.setText(getTitle("technologies") + proj.getTechnologiesUsed());
            run.addBreak();
        }
    }

    private void addCertifications(Resume resume) {
        if (!resume.getCertifications().isEmpty()) {
            addSection(getTitle("certifications"),
                    String.join("\n• ",
                            resume.getCertifications()));
        }
    }

    private void addLanguages(Resume resume) {
        if (!resume.getLanguages().isEmpty()) {
            addSection(getTitle("languages"), null);
            for (Language lang : resume.getLanguages()) {
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText(lang.getName() + " - " + lang.getProficiency());
                run.addBreak();
            }
        }
    }

    @Override
    public void close() throws IOException {
        if (document != null) {
            document.close();
            document = null;
        }
    }

    public void askToExit() {
        System.out.println("\nDigite ';' e pressione ENTER para fechar o programa:");
        try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
            String input;
            do {
                input = scanner.nextLine();
                if (!input.equals(";")) {
                    System.out.println("Para fechar o programa, digite ';' e pressione ENTER:");
                }
            } while (!input.equals(";"));
        }
    }
}
