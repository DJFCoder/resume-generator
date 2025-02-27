package com.devjf.curriculum.generator.service;

import com.devjf.curriculum.generator.model.*;
import java.util.Scanner;

public class ResumeBuilder {
    private final Scanner scanner;
    private final Resume resume;
    private String selectedLanguage; // Renomeado para maior clareza

    public ResumeBuilder() {
        this.scanner = new Scanner(System.in);
        this.resume = new Resume();
    }

    public Resume buildResume() {
        System.out.println("=== Gerador de Currículo para Desenvolvedor ===\n");
        collectPersonalInfo();
        System.out.println("\n--- Seleção de Idioma ---");
        System.out.println("Deseja gerar os títulos do currículo em inglês? (S/N)");
        selectedLanguage = selectResumeLanguage(scanner.nextLine()); // Armazena o resultado
        collectProfessionalSummary();
        collectTechnicalSkills();
        collectExperiences();
        collectEducation();
        collectProjects();
        collectCertifications();
        collectLanguages();
        return resume;
    }

    protected String selectResumeLanguage(String selection) {
        while (!selection.equalsIgnoreCase("S") && !selection.equalsIgnoreCase("N")) {
            System.out.println("Opção inválida. Digite S para inglês ou N para português:");
            selection = scanner.nextLine();
        }
        return selection.equalsIgnoreCase("S") ? "English" : "Portuguese";
    }

    public String getSelectedLanguage() {
        return selectedLanguage; // Retorna o idioma já selecionado
    }

    private void collectPersonalInfo() {
        System.out.println("--- Informações Pessoais ---");
        System.out.print("Nome completo: ");
        resume.setName(scanner.nextLine());
        System.out.print("Email: ");
        resume.setEmail(scanner.nextLine());
        System.out.print("Telefone: ");
        resume.setPhone(scanner.nextLine());
        System.out.print("LinkedIn URL: ");
        resume.setLinkedin(scanner.nextLine());
        System.out.print("GitHub URL: ");
        resume.setGithub(scanner.nextLine());
    }

    private void collectProfessionalSummary() {
        System.out.println("\n--- Resumo Profissional ---");
        System.out.println(
                "Digite seu resumo profissional (máximo 500 caracteres):");
        resume.setProfessionalSummary(scanner.nextLine());
    }

    private void collectTechnicalSkills() {
        System.out.println("\n--- Competências Técnicas ---");
        System.out.println(
                "Digite suas competências (digite ';' para encerrar):");
        while (true) {
            String skill = scanner.nextLine();
            if (skill.equalsIgnoreCase(";")) {
                break;
            }
            resume.getTechnicalSkills().add(skill);
        }
    }

    private void collectExperiences() {
        System.out.println("\n--- Experiência Profissional ---");
        System.out.println("Digite suas experiências (digite ';' para encerrar)");
        while (true) {
            System.out.print("\nEmpresa (ou ';' para encerrar): ");
            String company = scanner.nextLine();
            if (company.equalsIgnoreCase(";")) {
                break;
            }
            Experience exp = new Experience();
            exp.setCompany(company);
            System.out.print("Cargo: ");
            exp.setPosition(scanner.nextLine());
            System.out.print("Período (ex: Jan 2020 - Atual): ");
            exp.setPeriod(scanner.nextLine());
            System.out.print("Descrição das atividades: ");
            exp.setDescription(scanner.nextLine());
            resume.getExperiences().add(exp);
        }
    }

    private void collectEducation() {
        System.out.println("\n--- Formação Acadêmica ---");
        System.out.println("Digite suas formações (digite ';' para encerrar)");
        while (true) {
            System.out.print("\nInstituição (ou ';' para encerrar): ");
            String institution = scanner.nextLine();
            if (institution.equalsIgnoreCase(";")) {
                break;
            }
            System.out.print("Curso: ");
            String course = scanner.nextLine();
            System.out.print("Tipo (ex: Bacharelado, Técnico): ");
            String type = scanner.nextLine();
            System.out.print("Período (ex: 2018 - 2022): ");
            String period = scanner.nextLine();
            resume.getEducation().add(new Education(institution,
                    course,
                    period,
                    type));
        }
    }

    private void collectProjects() {
        System.out.println("\n--- Projetos ---");
        System.out.println("Digite seus projetos (digite ';' para encerrar)");
        while (true) {
            System.out.print("\nNome do projeto (ou ';' para encerrar): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase(";")) {
                break;
            }
            System.out.print("Descrição: ");
            String description = scanner.nextLine();
            System.out.print("Tecnologias utilizadas: ");
            String technologies = scanner.nextLine();
            resume.getProjects().add(new Project(name,
                    description,
                    technologies));
        }
    }

    private void collectCertifications() {
        System.out.println("\n--- Certificações ---");
        System.out.println(
                "Digite suas certificações (digite ';' para encerrar):");
        while (true) {
            String certification = scanner.nextLine();
            if (certification.equalsIgnoreCase(";")) {
                break;
            }
            resume.getCertifications().add(certification);
        }
    }

    private void collectLanguages() {
        System.out.println("\n--- Idiomas ---");
        System.out.println("Digite seus idiomas (digite ';' para encerrar)");
        while (true) {
            System.out.print("\nIdioma (ou ';' para encerrar): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase(";")) {
                break;
            }
            System.out.print("Nível de proficiência: ");
            String proficiency = scanner.nextLine();
            resume.getLanguages().add(new Language(name,
                    proficiency));
        }
    }
}
