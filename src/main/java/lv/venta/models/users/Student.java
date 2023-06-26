package lv.venta.models.users;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.venta.models.StudyProgram;
import lv.venta.models.Thesis;

@Table(name = "student_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name="Idp", column = @Column(name="Ids"))
public class Student extends Person {

	// TODO izveidot DATA JPA anotācijas
	// izveidot validāciju anotācijas
	// izveidot sasaisti ar course klasi manytoomany

	@Column(name="matriculaNo")
	@NotNull 
	@Size(min=8,max=20)
	@Pattern(regexp="[0-9]{8,20}")
	private String matriculaNo;

	@NotNull
	@Column(name="FinancialDebt")
	private boolean financialDebt;
	
	@NotNull
	@Column(name = "birthYear")
	@Past(message = "Date of birth must be in the past")
	private LocalDate dob;
	
	@NotNull
	@Column(name="academicStatus")
	@Enumerated(EnumType.STRING)
	private AcademicStatus academicStatus;
	
	@NotNull
	@Column(name = "budget")
	private boolean budget;
	
	@OneToOne(mappedBy="assignedStudent")
	private Thesis thesis;
	

	@ManyToOne
	@JoinColumn(name = "Idsp")
	private StudyProgram studyProgram;
	
	@ManyToMany
	@JoinTable(name="student_thesis_application_table",
	joinColumns = @JoinColumn(name="Idt"),
	inverseJoinColumns = @JoinColumn(name="Idp"))
	private Collection <Thesis> appliedThesis = new ArrayList<>();
	
	
	public Student(
			@Pattern(regexp = "[A-ZĀČĒĪĶĻŅŠŪŽ]{1}[a-zāčēīķļņšūž\\ ]+", message = "Pirmajam burtam jābūt lielajam") @NotNull @Size(min = 3, max = 15) String name,
			@NotNull @Size(min = 3, max = 15) @Pattern(regexp = "[A-ZĀČĒĪĶĻŅŠŪŽ]{1}[a-zāčēīķļņšūž\\ ]+", message = "Pirmajam burtam jābūt lielajam") String surname,
			@NotNull @Size(min = 12, max = 12) @Pattern(regexp = "[0-9]{6}-[0-9]{5}", message = "Neatbilstošs personas kods!") String personCode,
			User user, @NotNull @Size(min = 8, max = 20) @Pattern(regexp = "[0-9]{8,20}") String matriculaNo,
			@NotNull boolean financialDebt,
			@NotNull @Min(value = 1950, message = "Date of Birth must be greater than or equal to 1950") @Past(message = "Date of Birth must be in the past") LocalDate dob,
			@NotNull AcademicStatus academicStatus, @NotNull boolean budget, StudyProgram studyProgram) {
		super(name, surname, personCode, user);
		this.matriculaNo = matriculaNo;
		this.financialDebt = financialDebt;
		this.dob = dob;
		this.academicStatus = academicStatus;
		this.budget = budget;
		this.studyProgram = studyProgram;
	}
	
	
	
	

}

