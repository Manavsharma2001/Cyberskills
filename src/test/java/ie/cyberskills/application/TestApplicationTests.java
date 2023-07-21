package ie.cyberskills.application;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

//importing Student Entity and StudentRepository
import ie.cyberskills.application.entity.Student;
import ie.cyberskills.application.repository.StudentRepository;
//importing Course Entity and CourseRepository
import ie.cyberskills.application.entity.Course;
import ie.cyberskills.application.repository.CourseRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TestApplicationTests {
	//We initalize the repositry as a mock to simulate its behavior down the road
    @Mock
    private StudentRepository studentRepository;
	//We make a function which will be used in our test execution
    @Test
    void testFindByJoinCourses() {
		//Creating test data to be used within the Unit Test
        boolean joinCourses = true;
		//Using List to mock a database
        List<Student> students = new ArrayList<>();
		//Making to students to be added to the list
        students.add(new Student("Maksims Kazoha", "9 inis allain Bandon", "086 394 5665", joinCourses));
        students.add(new Student("Will Smith", "6969 main st bandon", "086 283 3931", joinCourses));
		//We use the mocked repository and find join courses by using list as database and checking by join courses
        when(studentRepository.findByJoinCourses(joinCourses)).thenReturn(students);
		//We check if the repository contains students with joinCourses as true and store it in result
        List<Student> result = studentRepository.findByJoinCourses(joinCourses = true);
		//We use assertion to check if the expected result is the same as the result we made
        Assertions.assertEquals(2, result.size());
    }
	//We initalize the repositry as a mock to simulate its behavior down the road
	@Mock
	private CourseRepository courseRepository;
	//We create dummy student for testing
	private Student student;

	@Test
	void testFindByStudentId(){
		//we require CourseId for creating courses and decided to make two
		long courseId1 = 31202L;
		long courseId2 = 31102;
		//we also need a student id for looking up a student
		long studentId = 188979;
		//we make an empty array as mock database
		List<Course> courses = new ArrayList<>();
		//we add two courses
		courses.add(new Course(courseId1 ,"Maths", "Maths Description", student));
		courses.add(new Course(courseId2 ,"Cyber Analytics", "Cyber Analytics Description", student));
		//we check for a student using findByStudentId then return the courses beloning to the student
		when(courseRepository.findByStudentId(studentId)).thenReturn(courses);
		//we store the result of findByStudentId into result for later tests
		List<Course> result = courseRepository.findByStudentId(studentId);
		//we compare the expected result with the result we got from using findByStudentId
		Assertions.assertEquals(2, result.size());
	}

}
