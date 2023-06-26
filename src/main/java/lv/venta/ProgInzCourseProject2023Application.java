package lv.venta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.models.Thesis;
import lv.venta.models.users.AcademicPersonel;
import lv.venta.models.users.Degree;
import lv.venta.models.users.Student;
import lv.venta.models.users.User;
import lv.venta.repos.IThesisRepo;
import lv.venta.repos.users.IAcademicPersonelRepo;
import lv.venta.repos.users.IStudentRepo;
import lv.venta.repos.users.IUserRepo;

@SpringBootApplication
public class ProgInzCourseProject2023Application {

	public static void main(String[] args) {
		SpringApplication.run(ProgInzCourseProject2023Application.class, args);
	}

	/*@Bean // izsauks funkciju automātiski, kad sistēma tiks startēta
	public CommandLineRunner testModel(IUserRepo userRepo, IAcademicPersonelRepo personRepo, IStudentRepo studentRepo, 
			IAcademicPersonelRepo personelRepo, ICourseRepo courseRepo, IThesisRepo thesisRepo) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				User us1 = new User("123", "karina.krinkele@venta.lv");
				User us2 = new User("123", "karlis.immers@venta.lv");
				User us3 = new User("123", "students.pirmais@venta.lv");
				User us4 = new User("123", "studente.otra@venta.lv");
				userRepo.save(us1);
				userRepo.save(us2);
				userRepo.save(us3);
				userRepo.save(us4);
				
				Course c1 = new Course("Java", 4);
				Course c2 = new Course("HTML", 4);
				courseRepo.save(c1);
				courseRepo.save(c2);

				
				AcademicPersonel ac1 = new AcademicPersonel("Karina", "Skirmante", "123456-12345", us1, Degree.mg);
				AcademicPersonel ac2 = new AcademicPersonel("Karlis", "Immers", "654321-54321", us2, Degree.mg);
				personRepo.save(ac1);
				personRepo.save(ac2);
				

				Student st1 = new Student("Janis", "Berzins", "211221-34567", us3, "12345678", false);
				Student st2 = new Student("Ieva", "Kalniņa", "152813-95756", us4, "81253525", true);
				st2.addDebtCourse(c1);
				st2.addDebtCourse(c2);
				studentRepo.save(st1);
				studentRepo.save(st2);
				c1.addStudent(st2);
				c2.addStudent(st2);
				courseRepo.save(c1);
				courseRepo.save(c2);

				Thesis th1 = new Thesis("Sistēmas izstrāde", "Development of System", "Development", "1...2.3..4",st1, ac1);
				Thesis th2 = new Thesis("Petījums", "Research", "Research", "1...2.3..4",st2, ac2);
				
				th1.addReviewer(ac2);
				th1.addReviewer(ac1);
				thesisRepo.save(th1);
				thesisRepo.save(th2);
				ac1.addThesisForReviews(th2);
				ac2.addThesisForReviews(th1);
				personRepo.save(ac1);
				personRepo.save(ac2);


			}

		};

	}*/

}
