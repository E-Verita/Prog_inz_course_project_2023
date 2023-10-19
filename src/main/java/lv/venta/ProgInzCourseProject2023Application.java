package lv.venta;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import lv.venta.models.Area;
import lv.venta.models.Complexity;
import lv.venta.models.Level;
import lv.venta.models.StudyProgram;
import lv.venta.models.StudyType;
import lv.venta.models.Thesis;
import lv.venta.models.security.MyAuthority;
import lv.venta.models.users.AcademicPersonel;
import lv.venta.models.users.AcademicStatus;
import lv.venta.models.users.Degree;
import lv.venta.models.users.Student;
import lv.venta.models.users.User;
import lv.venta.repo.security.IMyAuthorityRepo;
import lv.venta.repos.ICommentRepo;
import lv.venta.repos.IITFBoardMeetingRepo;
import lv.venta.repos.IMeetingMemberRepo;
import lv.venta.repos.IStudyProgramRepo;
import lv.venta.repos.IThesisApplicationRepo;
import lv.venta.repos.IThesisRepo;
import lv.venta.repos.users.IAcademicPersonelRepo;
import lv.venta.repos.users.IPersonRepo;
import lv.venta.repos.users.IStudentRepo;
import lv.venta.repos.users.IUserRepo;

@SpringBootApplication
public class ProgInzCourseProject2023Application implements WebMvcConfigurer {

	/*public static void main(String[] args) {
		SpringApplication.run(ProgInzCourseProject2023Application.class, args);
	} */
	
	private final LocaleChangeInterceptor localeChangeInterceptor;
	
	
	
	public ProgInzCourseProject2023Application(LocaleChangeInterceptor localeChangeInterceptor) {
		this.localeChangeInterceptor = localeChangeInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry interceptorRegistry) {
		interceptorRegistry.addInterceptor(localeChangeInterceptor);
	}
	
	 
	public static void main(String[] args) {
		
		// enable i18
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasenames("lang/messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    System.out.println(messageSource.getMessage("hello", null, Locale.ROOT));
	    SpringApplication.run(ProgInzCourseProject2023Application.class, args);
	  }

	//@Bean // izsauks funkciju automātiski, kad sistēma tiks startēta
	public CommandLineRunner testModel(IUserRepo userRepo, IAcademicPersonelRepo personelRepo, IStudentRepo studentRepo,
			IPersonRepo personRepo, ICommentRepo commentRepo, IITFBoardMeetingRepo meetingRepo,
			IMeetingMemberRepo memberRepo, IStudyProgramRepo programRepo, IThesisRepo thesisRepo,
			IThesisApplicationRepo applicationRepo, IMyAuthorityRepo authorityRepo) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				User idu1 = new User("Karina", "Skirmante", passwordEncoderSimple().encode("123"));
				User idu2 = new User("Karlis", "Immers", passwordEncoderSimple().encode("123"));
				User idu3 = new User("Estere", "Vitola", passwordEncoderSimple().encode("123"));

				User idu4 = new User("Students", "Pirmais", passwordEncoderSimple().encode("123"));
				User idu5 = new User("Students", "Otrais", passwordEncoderSimple().encode("123"));
				User idu6 = new User("Students", "Tresais", passwordEncoderSimple().encode("123"));
				User idu7 = new User("Students", "Ceturtais", passwordEncoderSimple().encode("123"));

				userRepo.save(idu1);
				userRepo.save(idu2);
				userRepo.save(idu3);
				userRepo.save(idu4);
				userRepo.save(idu5);
				userRepo.save(idu6);
				userRepo.save(idu7);

				AcademicPersonel ida1 = new AcademicPersonel("Karina", "Skirmante", "123456-12345", idu1, Degree.mg);
				AcademicPersonel ida2 = new AcademicPersonel("Karlis", "Immers", "654321-54321", idu2, Degree.mg);
				AcademicPersonel ida3 = new AcademicPersonel("Estere", "Vitola", "543211-12345", idu3, Degree.mg);

				personRepo.save(ida1);
				personRepo.save(ida2);
				personRepo.save(ida3);

				// --- security ----

				MyAuthority auth1 = new MyAuthority("STUDENT");
				MyAuthority auth2 = new MyAuthority("PROFESSOR");

				auth1.addUser(idu4);
				auth1.addUser(idu5);
				auth1.addUser(idu6);
				auth1.addUser(idu7);
				auth2.addUser(idu1);
				auth2.addUser(idu2);
				auth2.addUser(idu3);
				authorityRepo.save(auth1);
				authorityRepo.save(auth2);

				idu4.addAuthority(auth1);
				idu5.addAuthority(auth1);
				idu6.addAuthority(auth1);
				idu7.addAuthority(auth1);
				idu1.addAuthority(auth2);
				idu2.addAuthority(auth2);
				idu3.addAuthority(auth2);

				userRepo.save(idu1);
				userRepo.save(idu2);
				userRepo.save(idu3);
				userRepo.save(idu4);
				userRepo.save(idu5);
				userRepo.save(idu6);
				userRepo.save(idu7);

				// --- ----

				StudyProgram idsp1 = new StudyProgram("Programmēšanas speciālists", ida3, StudyType.professional,
						Level.first_level, 4, 4, 2023, 2024);
				programRepo.save(idsp1);

				Student ids1 = new Student("Students", "Pirmais", "111111-11111", idu4, "sp01234", false,
						LocalDate.of(1991, 10, 4), AcademicStatus.studying, false, idsp1);
				Student ids2 = new Student("Students", "Otrais", "222222-22222", idu5, "sp01234", false,
						LocalDate.of(1991, 01, 1), AcademicStatus.studying, false, idsp1);
				Student ids3 = new Student("Students", "Tresais", "333333-33333", idu6, "sp56789", false,
						LocalDate.of(1998, 07, 4), AcademicStatus.studying, false, idsp1);
				Student ids4 = new Student("Students", "Ceturtais", "444444-44444", idu7, "sp24680", false,
						LocalDate.of(2002, 04, 5), AcademicStatus.studying, false, idsp1);

				studentRepo.save(ids1);
				studentRepo.save(ids2);
				studentRepo.save(ids3);
				studentRepo.save(ids4);

				Thesis idt1 = new Thesis("Blockchain tehnoloģijas izmantošana drošības uzlabošanai finanšu nozarē",
						"Use of blockchain technology to enhance security in the financial industry",
						Arrays.asList(Area.security, Area.finance, Area.blockchain), Complexity.average,
						"Kvalifikācijas darba izstrādes laikā students pētīs, kā blockchain tehnoloģijas var palīdzēt uzlabot drošību finanšu nozarē. Analizēs, kā blockchain nodrošina drošību, kā tas palīdz izvairīties no krāpniecības un kā blockchain var tikt izmantots finanšu nozarē, lai uzlabotu procesus un nodrošinātu lielāku drošību darījumu veikšanā.\r\n"
								+ "Studentam, kas interesējas par šo tēmu, varētu būt sagaidāmas priekšzināšanas, kas ir blockchain tehnoloģijas un kādas ir tās priekšrocības drošības uzlabošanai finanšu nozarē. Varētu arī būt interesējošs jautājums par to, kā blockchain var tikt pielietots citās nozarēs, piemēram, medicīnā vai publiskajā sektorā.",
						ida1);

				Thesis idt2 = new Thesis("Datoru tīklu drošības risinājumu analīze un izvērtējums",
						"Analysis and evaluation of computer network security solutions",
						Arrays.asList(Area.security, Area.network), Complexity.average,
						"Kvalifikācijas darba mērķis ir izpētīt un analizēt dažādus datoru tīklu drošības risinājumus, to efektivitāti un piemērošanas jomas. Darba gaitā tiks veikta salīdzinoša analīze starp dažādiem drošības protokoliem, mehānismiem un algoritmiem, lai noskaidrotu to atbilstību datoru tīklu drošības prasībām. Galvenais uzdevums ir izvērtēt esošos risinājumus un sniegt ieteikumus par to piemērošanu un uzlabošanu.",
						ida1);

				Thesis idt3 = new Thesis("Digitālās identitātes pārvaldības risinājumi un to pielietojums",
						"Digital identity management solutions and their application",
						Arrays.asList(Area.security, Area.development), Complexity.average,
						"Kvalifikācijas darbā tiks pētīti un analizēti dažādi digitālās identitātes pārvaldības risinājumi, to priekšrocības un iespējamās problēmas. Tiks aplūkotas dažādas tehnoloģijas, protokoli un standarti, kas tiek izmantoti digitālās identitātes pārvaldībā, kā arī to pielietojums dažādās nozarēs un lietojumprogrammās. Galvenais mērķis ir izstrādāt ieteikumus un vadlīnijas digitālās identitātes pārvaldības risinājumu ieviešanai un uzlabošanai.",
						ida1);

				Thesis idt4 = new Thesis("Sociālo tīklu ietekme uz sabiedrību un indivīdu privātumu",
						"The impact of social networks on society and individual privacy",
						Arrays.asList(Area.social, Area.privacy), Complexity.very_complex,
						"The qualification work will investigate the influence of social networks on society and the implications for individual privacy. It will analyze the effects of social media platforms on social interactions, communication patterns, and information sharing. The work will examine the privacy concerns associated with the collection, storage, and sharing of personal data on social networks and explore the ethical and legal considerations related to privacy protection. The main objective is to propose strategies for individuals and organizations to enhance privacy and mitigate the negative consequences of social network usage.",
						ida1);

				Thesis idt5 = new Thesis("Biometriskās identifikācijas tehnoloģijas un to drošības aspekti",
						"Biometric identification technologies and their security aspects",
						Arrays.asList(Area.security, Area.biometrics), Complexity.complex,
						"The qualification work aims to explore and analyze various biometric identification technologies, their advantages, and potential security issues. It will examine different biometric modalities such as fingerprints, iris recognition, and facial recognition, as well as the challenges and privacy concerns associated with biometric data. The main objective is to evaluate the security aspects of biometric identification technologies and propose recommendations for their secure implementation and usage.",
						ida2);

				Thesis idt6 = new Thesis("Virtuālās valūtas un blokķēdes tehnoloģiju ietekme uz finanšu nozarēm",
						"The impact of virtual currencies and blockchain technologies on the financial sector",
						Arrays.asList(Area.finance, Area.technology), Complexity.very_complex,
						"The qualification work will explore the influence of virtual currencies, such as Bitcoin, and blockchain technologies on the financial sector. It will analyze the advantages and challenges of using virtual currencies for financial transactions, as well as the potential applications of blockchain technology in areas such as payment systems, smart contracts, and identity verification. The main goal is to assess the impact of these technologies on financial institutions and propose strategies for their effective integration into the existing financial infrastructure.",
						ida2);

				Thesis idt7 = new Thesis("Mobilās lietotnes izstrāde un to lietojums mācību procesā",
						"Mobile application development and its application in the learning process",
						Arrays.asList(Area.development, Area.education), Complexity.average,
						"The qualification work will focus on the development of mobile applications and their application in the learning process. It will explore the use of mobile technologies, such as smartphones and tablets, in educational settings and analyze the benefits and challenges of mobile learning. The work will involve designing and implementing a mobile application prototype that supports learning activities and evaluating its effectiveness in enhancing the learning experience. The main objective is to provide recommendations for the development and integration of mobile applications in educational institutions.",
						ida2);

				Thesis idt8 = new Thesis("Datu analīzes metožu izmantošana biznesa lēmumu pieņemšanā",
						"Utilization of data analysis methods in business decision-making",
						Arrays.asList(Area.analytics, Area.business), Complexity.easy,
						"The qualification work will investigate the utilization of data analysis methods in the context of business decision-making. It will explore various data analysis techniques, such as statistical analysis, machine learning, and data visualization, and their application in solving business problems. The work will involve collecting and analyzing real-world business data and using appropriate data analysis tools to derive insights and support decision-making processes. The main goal is to provide recommendations for the effective use of data analysis methods in business environments.",
						ida2);

				thesisRepo.save(idt1);
				thesisRepo.save(idt2);
				thesisRepo.save(idt3);
				thesisRepo.save(idt4);
				thesisRepo.save(idt5);
				thesisRepo.save(idt6);
				thesisRepo.save(idt7);
				thesisRepo.save(idt8);

			}

		};

	}

	//@Bean
	public PasswordEncoder passwordEncoderSimple() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}