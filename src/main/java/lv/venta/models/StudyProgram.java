package lv.venta.models;

import java.time.LocalDateTime;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.venta.models.users.AcademicPersonel;
import lv.venta.models.users.Student;
import lv.venta.models.users.User;

@Table(name = "study_program_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudyProgram {
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idsp")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idsp;
	
	@Column(name = "Title")
	@Size(min = 3, max = 250, message = "Title of study program needs to be 3 - 250 char long")
	@NotNull(message="Title field cannot be empty")
	private String title;
	
	@OneToOne
	@JoinColumn(name="Ida")
	private AcademicPersonel director;
	
	@NotNull
	@Column(name="studytype")
	@Enumerated(EnumType.STRING)
	private StudyType studytype;
	
	@NotNull
	@Column(name="level")
	@Enumerated(EnumType.STRING)
	private Level level;
	
	@Column(name = "noOfSemester")
	@Size(min = 4, max = 12, message = "Number of semesters are 4-12")
	@NotNull(message="Field cannot be empty")
	private int noOfSemester;
	
	@Column(name = "thesisSemester")
	@Size(min = 4, max = 12, message = "Semester no. for thesis can be 4-12")
	@NotNull(message="Field cannot be empty")
	private int thesisSemester;
	
	@NotNull
	@Column(name = "start")
	@Min(value = 2020, message = "Start year must be greater than or equal to 2020")
	private int startYear;
	
	@NotNull
	@Column(name = "grad")
	@Min(value = 2023, message = "Graduation year must be greater than or equal to 2023")
	@FutureOrPresent(message = "Graduation year must be today or in the future")
	private int gradYear;
	
	@OneToMany(mappedBy="studyProgram")
	private Collection <Student> students;

	public StudyProgram(
			@Size(min = 3, max = 250, message = "Title of study program needs to be 3 - 250 char long") @NotNull(message = "Title field cannot be empty") String title,
			AcademicPersonel director, @NotNull StudyType studytype, @NotNull Level level,
			@Size(min = 4, max = 12, message = "Number of semesters are 4-12") @NotNull(message = "Field cannot be empty") int noOfSemester,
			@Size(min = 4, max = 12, message = "Semester no. for thesis can be 4-12") @NotNull(message = "Field cannot be empty") int thesisSemester,
			@NotNull @Min(value = 2020, message = "Start year must be greater than or equal to 2020") @FutureOrPresent(message = "Start year must be today or in the future") int startYear,
			@NotNull @Min(value = 2023, message = "Graduation year must be greater than or equal to 2023") @FutureOrPresent(message = "Graduation year must be today or in the future") int gradYear) {
		super();
		this.title = title;
		this.director = director;
		this.studytype = studytype;
		this.level = level;
		this.noOfSemester = noOfSemester;
		this.thesisSemester = thesisSemester;
		this.startYear = startYear;
		this.gradYear = gradYear;
	}
	
	
	
}
