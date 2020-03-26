package sk.upjs.nosql_redis_repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import sk.upjs.nosql_data_source.entity.Student;
import sk.upjs.nosql_data_source.persist.DaoFactory;
import sk.upjs.nosql_data_source.persist.StudentDao;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		StudentRepository repository = context.getBean(StudentRepository.class);
		StudentDao studentDao = DaoFactory.INSTANCE.getStudentDao();
		List<Student> students = studentDao.getAll();
//		RedisStudent redisStudent = new RedisStudent(students.get(0));
//		repository.save(redisStudent);
//		System.out.println("Pocet studentov: " + repository.count());
//		Optional<RedisStudent> foundStudent = repository.findById(redisStudent.getId());
//		System.out.println("Found student: " + foundStudent);
//		List<RedisStudent> foundByPriezvisko = repository.findByPriezvisko("Najahalovu");
//		System.out.println("Found student: " + foundByPriezvisko);
//		repository.delete(redisStudent);
//		System.out.println("Pocet studentov: " + repository.count());

		List<RedisStudent> redisStudents = students.stream().map(student -> new RedisStudent(student))
				.collect(Collectors.toList());
		repository.saveAll(redisStudents);
		System.out.println("Pocet studentov: " + repository.count());
		for (RedisStudent student: repository.findAll()) {
			if (student.getStudium().size() > 1) {
				System.out.println(student);
			}
		}
		context.close();
	}
}
