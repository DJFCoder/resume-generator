# Resume Generator

A simple command-line tool that generates professional resumes in DOCX format, specifically designed for developers.

## Overview

This Resume Generator is a Java application that helps developers create well-structured resumes by inputting their information through an interactive console interface. The generated resume is saved as a DOCX file with consistent formatting.

## Features

- Interactive console-based input
- Bilingual support (English/Portuguese) for section titles
- Generates professionally formatted DOCX files
- Sections included:
  - Personal Information
  - Professional Summary
  - Technical Skills
  - Professional Experience
  - Education
  - Projects
  - Certifications
  - Languages

## Technical Details

- Built with Java 17
- Uses Apache POI for DOCX file generation
- Lombok for reducing boilerplate code
- Maven for project management

## Requirements

- Java 17 or higher
- Windows operating system
- Maven (for building from source)

## Usage

1. Download the executable file
2. Run the application
3. Follow the interactive prompts to input your information
4. The generated resume will be saved as a DOCX file in your specified location

## Building from Source

```bash
mvn clean package
```

## Releases
### Version 1.0
- Initial release
- Available as executable (.exe) file for Windows
- Features complete resume generation with all sections
- Bilingual support (English/Portuguese)
Download the latest release from the [releases page](https://github.com/DJFCoder/resume-generator/releases/tag/published).