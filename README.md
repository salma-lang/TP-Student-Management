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

## Description des Classes et Interfaces

### 1. **Student.java (Entité)**

Cette classe représente un **étudiant** dans le système. Elle est annotée avec `@Entity` pour indiquer qu'il s'agit d'une entité JPA.

```java
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    // Getters et Setters
}
```
- **id** : Identifiant unique de l'étudiant (généré automatiquement).
- **nom** : Le nom de l'étudiant.
- **prenom** : Le prénom de l'étudiant.
- **dateNaissance** : La date de naissance de l'étudiant.

---

### 2. **StudentController.java (Contrôleur)**

Le contrôleur gère les requêtes HTTP et dirige les actions vers les services correspondants. Il est annoté avec `@RestController`, ce qui signifie qu'il gère des requêtes REST.

```java
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        Student savedStudent = studentService.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        boolean deleted = studentService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> findAll() {
        List<Student> students = studentService.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countStudents() {
        long count = studentService.countStudents();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/byYear")
    public ResponseEntity<Collection<?>> findNbrStudentByYear() {
        Collection<?> studentsByYear = studentService.findNbrStudentByYear();
        return new ResponseEntity<>(studentsByYear, HttpStatus.OK);
    }
}
```

Les points d'entrée de l'API sont :
- `POST /students/save` : pour ajouter un étudiant.
- `DELETE /students/delete/{id}` : pour supprimer un étudiant.
- `GET /students/all` : pour obtenir tous les étudiants.
- `GET /students/count` : pour compter le nombre d'étudiants.
- `GET /students/byYear` : pour obtenir le nombre d'étudiants par année de naissance.

---

### 3. **StudentRepository.java (Repository)**

Cette interface étend `JpaRepository`, qui fournit des méthodes CRUD de base. Elle inclut aussi une requête personnalisée pour obtenir le nombre d'étudiants par année de naissance.

```java
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Recherche d'un étudiant par son identifiant
    Student findById(int id);

    // Requête personnalisée pour compter les étudiants par année de naissance
    @Query("SELECT YEAR(s.dateNaissance), COUNT(s) FROM Student s GROUP BY YEAR(s.dateNaissance)")
    Collection<Object[]> findNbrStudentByYear();
}
```

---

### 4. **StudentService.java (Service)**

Le service contient la logique métier et fait le lien avec le repository pour interagir avec la base de données.

```java
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public boolean delete(int id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public long countStudents() {
        return studentRepository.count();
    }

    public Collection<?> findNbrStudentByYear() {
        return studentRepository.findNbrStudentByYear();
    }
}
```

---

## Tests Unitaires

Les tests unitaires ont été réalisés pour tester les fonctionnalités du contrôleur. Les tests utilisent **Mockito** pour simuler le comportement du service.

Voici quelques exemples de tests :

- **`testSaveStudent()`** : Vérifie que l'ajout d'un étudiant fonctionne correctement.
- **`testDeleteStudent()`** : Vérifie que la suppression d'un étudiant fonctionne correctement.
- **`testFindAllStudents()`** : Vérifie que la récupération de tous les étudiants fonctionne.
- **`testCountStudents()`** : Vérifie que le comptage des étudiants fonctionne.
- **`testFindByYear()`** : Vérifie que le comptage des étudiants par année de naissance fonctionne.

### Résultats des Tests

Voici les résultats après l'exécution des tests avec succès :

![image](https://github.com/user-attachments/assets/99041d04-4ce7-440d-932d-9e2d55ce51a6)


---

## API Tests avec **Advanced REST Client (ARC)**

### 1. **GET /students/all**

Cette requête retourne une liste de tous les étudiants. Exemple de réponse :

```json
[
  {
    "id": 3,
    "nom": "JALAT",
    "prenom": "salma",
    "dateNaissance": "2003-07-03"
  },
  {
    "id": 4,
    "nom": "ALAOUI",
    "prenom": "Amina",
    "dateNaissance": "2003-08-06"
  },
  {
    "id": 6,
    "nom": "EL IDRISSI",
    "prenom": "Hanane",
    "dateNaissance": "2003-09-10"
  }
]
```

![image](https://github.com/user-attachments/assets/f44a6375-4cd2-4548-a84d-54be19c46e8d)


### 2. **GET /students/{id}**

Cette requête retourne les détails d'un étudiant par son ID. Exemple pour l'ID 4 :

```json
{
  "id": 4,
  "nom": "ALAOUI",
  "prenom": "Amina",
  "dateNaissance": "2003-08-06"
}
```
![image](https://github.com/user-attachments/assets/8e45b44b-a915-4df6-adce-ea4c9c8b2c66)


---

## Documentation API avec **Swagger**

L'application expose également la documentation Swagger à l'URL suivante :  
`http://localhost:8080/swagger-ui.html`

Swagger vous permet d'explorer et de tester les différentes routes de l'API directement depuis l'interface web comme indiqué ci-dessous:

![image](https://github.com/user-attachments/assets/9aa35492-67f2-4815-b982-f3a10b4431f0)


---

## Conclusion

Ce projet est une démonstration de gestion d'étudiants en utilisant **Spring Boot**, avec un modèle MVC bien structuré. L'application inclut des fonctionnalités CRUD de base, une fonctionnalité pour compter les étudiants, et une autre pour les regrouper par année de naissance. Tous les tests ont été réussis et l'API est bien documentée avec **Swagger**.

---


