# Gestion des Étudiants- Spring Boot

## Description
Il s'agit d'un système de **gestion des étudiants** développé avec **Spring Boot**. Ce système permet de gérer les données des étudiants, telles que leur nom, prénom et date de naissance. Il prend en charge les opérations CRUD courantes ainsi que des fonctionnalités supplémentaires telles que le comptage des étudiants et leur regroupement par année de naissance.Ce projet suit un modèle MVC (Modèle-Vue-Contrôleur) et inclut une API REST qui peut être testée à l'aide d'outils comme Advanced REST Client et Swagger

## Fonctionnalités
- Ajouter un nouvel étudiant
- Supprimer un étudiant par son ID
- Afficher la liste de tous les étudiants
- Compter le nombre total d'étudiants
- Voir le nombre d'étudiants par année de naissance

## Technologies Utilisées
- **Spring Boot**
- **Spring Data JPA**
- **PhpMyAdmin** 
- **JUnit 5** 
- **Advanced REST Client (ARC)** 
- **Swagger** 

---

## Structure du Projet

```
student-management
├── .mvn
├── com
│   └── example
│       └── student_management
│           ├── controllers
│           │   └── StudentController.java
│           ├── entities
│           │   └── Student.java
│           ├── repositories
│           │   └── StudentRepository.java
│           ├── services
│           │   └── StudentService.java
│           └── StudentManagementApplication.java
├── resources
│   └── application.properties
├── test
│   └── java
│       └── com
│           └── example
│               └── student_management
│                   └── StudentControllerTest.java
└── mvnw
└── mvnw.cmd
└── .gitignore
└── HELP.md
```



L'application sera disponible à l'adresse suivante :  
`http://localhost:8080`

---

N'hésite pas à me faire savoir si tu souhaites ajouter plus d'informations ou des instructions supplémentaires !
