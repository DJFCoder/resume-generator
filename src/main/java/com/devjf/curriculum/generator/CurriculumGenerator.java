package com.devjf.curriculum.generator;

import com.devjf.curriculum.generator.model.Resume;
import com.devjf.curriculum.generator.service.DocxGenerator;
import com.devjf.curriculum.generator.service.ResumeBuilder;
import java.io.IOException;
import java.io.File;

/**
 *
 * @author devjf
 */
public class CurriculumGenerator {
    public static void main(String[] args) {
        try {
            ResumeBuilder builder = new ResumeBuilder();
            Resume resume = builder.buildResume();
            String selectedLanguage = builder.getSelectedLanguage();
            
            String userHome = System.getProperty("user.home");
            String documentsPath = userHome;
            String outputDir = documentsPath + "\\Curriculos";
            
            // Generate filename with timestamp
            String timestamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = String.format("curriculo_%s_%s.docx", resume.getName().replaceAll("[^a-zA-Z0-9]", "-"), timestamp);
            String path = outputDir + "\\" + fileName;
            
            // Create directory if it doesn't exist
            if (!new File(outputDir).mkdirs() && !new File(outputDir).exists()) {
                throw new IOException("Não foi possível criar o diretório: " + outputDir);
            }
            
            try (DocxGenerator generator = new DocxGenerator()) {
                generator.setLanguage(selectedLanguage);
                generator.generateResume(resume, path);
                
                // Validate file creation
                File generatedFile = new File(path);
                if (generatedFile.exists() && generatedFile.length() > 0) {
                    System.out.println("\nCurrículo gerado com sucesso!");
                    System.out.println("Local: " + path);
                    System.out.println("Tamanho: " + (generatedFile.length() / 1024) + " KB");
                    System.out.println("\nPressione ';' e ENTER para fechar o programa.");
                } else {
                    throw new IOException("Falha na geração do arquivo.");
                }
                
                generator.askToExit();
            }
        } catch (IOException | NoClassDefFoundError e) {
            System.err.println("Erro ao gerar currículo: " + e.getMessage());
            System.err.println("Por favor, verifique as permissões de escrita e tente novamente.");
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
