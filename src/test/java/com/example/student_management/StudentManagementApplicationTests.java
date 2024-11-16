package com.example.student_management;

import com.example.student_management.controllers.StudentController;
import com.example.student_management.entities.Student;
import com.example.student_management.services.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentControllerTest {

	@Mock
	private StudentService studentService;

	@InjectMocks
	private StudentController studentController;

	@Test
	void testSaveStudent() {
		// Créer un étudiant pour le test
		Student student = new Student();
		student.setId(1);
		student.setNom("Mido");

		// Simuler la sauvegarde
		when(studentService.save(any(Student.class))).thenReturn(student);

		// Appeler le contrôleur pour sauvegarder l'étudiant
		ResponseEntity<Student> response = studentController.save(student);

		// Vérifier le statut de la réponse et l'étudiant sauvegardé
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Mido", response.getBody().getNom());
	}

	@Test
	void testDeleteStudent() {
		// Simuler la suppression d'un étudiant
		when(studentService.delete(1)).thenReturn(true);

		// Appeler le contrôleur pour supprimer l'étudiant
		ResponseEntity<Void> response = studentController.delete(1);

		// Vérifier le statut de la réponse
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	void testFindAllStudents() {
		// Créer des étudiants fictifs
		Student student1 = new Student();
		Student student2 = new Student();

		// Simuler la récupération des étudiants
		when(studentService.findAll()).thenReturn(Arrays.asList(student1, student2));

		// Appeler le contrôleur pour récupérer tous les étudiants
		ResponseEntity<List<Student>> response = studentController.findAll();

		// Vérifier que la liste retournée contient bien les étudiants
		assertEquals(2, response.getBody().size());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testCountStudents() {
		// Simuler le comptage des étudiants
		when(studentService.countStudents()).thenReturn(10L);

		// Appeler le contrôleur pour compter les étudiants
		ResponseEntity<Long> response = studentController.countStudents();

		// Vérifier que le nombre retourné est correct
		assertEquals(10L, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testFindByYear() {
		// Simuler la récupération par année
		when(studentService.findNbrStudentByYear()).thenReturn(Arrays.asList());

		// Appeler le contrôleur pour récupérer le nombre d'étudiants par année
		ResponseEntity<Collection<?>> response = studentController.findNbrStudentByYear();

		// Vérifier que la collection retournée est vide
		assertEquals(0, response.getBody().size());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}

