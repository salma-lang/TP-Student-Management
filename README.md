# Gestion des Étudiants- Spring Boot

## Description
Il s'agit d'un système de **gestion des étudiants** développé avec **Spring Boot**. Ce système permet de gérer les données des étudiants, telles que leur nom, prénom et date de naissance. Il prend en charge les opérations CRUD courantes ainsi que des fonctionnalités supplémentaires telles que le comptage des étudiants et leur regroupement par année de naissance.

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

---

## Tests

### 1. Tests Unitaires

Les tests unitaires ont été exécutés avec **JUnit 5**. Tous les tests ont été passés avec succès, comme indiqué ci-dessous :

- **Résultats des Tests :**
  - `testSaveStudent()`
  - `testCountStudents()`
  - `testFindByYear()`
  - `testFindAllStudents()`
  - `testDeleteStudent()`

  **Statut des Tests :** 5/5 Tests réussis  
  **Temps d'exécution :** 93 ms

### 2. Tests avec **Advanced REST Client (ARC)**

Les tests avec **Advanced REST Client** ont été effectués pour vérifier les différentes fonctionnalités de l'API.

- **Test d'affichage de tous les étudiants**  
  `GET http://localhost:8080/students/all`  
  **Réponse :** Liste des étudiants

- **Test de récupération d'un étudiant par son ID**  
  `GET http://localhost:8080/students/{id}`  
  **Réponse :** Détails de l'étudiant demandé

### 3. Documentation API avec **Swagger**

Swagger est utilisé pour générer une documentation interactive de l'API. Après avoir démarré l'application, vous pouvez accéder à la documentation en vous rendant sur l'URL suivante :  
`http://localhost:8080/swagger-ui.html`

---

## Installation et Lancement du Projet

1. Clonez le dépôt Git.
2. Exécutez le fichier `mvnw` pour démarrer le projet.

**Commandes pour démarrer :**
```bash
./mvnw spring-boot:run
```

L'application sera disponible à l'adresse suivante :  
`http://localhost:8080`

---

N'hésite pas à me faire savoir si tu souhaites ajouter plus d'informations ou des instructions supplémentaires !
