# Student Grades Management System

A JavaFX desktop application for managing student records and reviewing class grade performance. This project is built with Java and Ant and includes a small model layer for student data and grade-collection operations.

## Features

- Add student records with an ID, name, and grade.
- Validate required fields, grade range (0–100), and duplicate student IDs.
- Search for a student by ID and remove a student from the list.
- Display student records in a table.
- Calculate class statistics: student count, average, highest grade, and lowest grade.
- Group grades into performance bands (Fail, Pass, Good, Very Good, Excellent) and visualize their distribution with a pie chart.
- Demonstrate collection operations such as checking whether a grade exists and finding its frequency.

## Built with

- Java
- JavaFX
- Apache Ant project files (NetBeans project structure)

## Getting started

1. Install a JDK compatible with the JavaFX version configured in the project, and install JavaFX if it is not bundled with your JDK.
2. Clone this repository:

   ```bash
   git clone https://github.com/mohammedAbuGhali/StudentGradesManagementSystem.git
   cd StudentGradesManagementSystem/StudentGradesManagementSystem
   ```

3. Open the project in NetBeans and run it, or build/run it with Ant after configuring the JavaFX libraries for your environment.

> JavaFX is not included in many modern JDK distributions. If the project does not launch, check the JavaFX library configuration in the IDE and the project build settings.

## Project structure

```text
StudentGradesManagementSystem/
├── .gitignore
└── StudentGradesManagementSystem/
    ├── build.xml
    ├── manifest.mf
    ├── nbproject/
    └── src/studentgradesmanagementsystem/
        ├── StudentGradesManagementSystem.java
        └── model/
```

## Notes

This is an educational project. Student data is held in memory while the application runs; no database or persistent storage is configured.
