package lv.venta.models.users;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.venta.models.Course;
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

	@Column(name="FinancialDebt")
	private boolean financialDebt;

	@ManyToMany
	@JoinTable(name="student_debt_courses_table",
	joinColumns = @JoinColumn(name="Idc"),
	inverseJoinColumns = @JoinColumn(name="Idp"))
	private Collection <Course> debtCourses = new ArrayList<>();
	
	@OneToMany(mappedBy="student")
	private Collection<Thesis> thesis;
	
	
	public Student(
			@Pattern(regexp = "[A-ZĀČĒĪĶĻŅŠŪŽ]{1}[a-zāčēīķļņšūž\\ ]+", message = "Pirmajam burtam jābūt lielajam") @NotNull @Size(min = 3, max = 15) String name,
			@NotNull @Size(min = 3, max = 15) @Pattern(regexp = "[A-ZĀČĒĪĶĻŅŠŪŽ]{1}[a-zāčēīķļņšūž\\ ]+", message = "Pirmajam burtam jābūt lielajam") String surname,
			@NotNull @Size(min = 12, max = 12) @Pattern(regexp = "[0-9]{6}-[0-9]{5}", message = "Neatbilstošs personas kods!") String personCode,
			User user, @NotNull @Size(min = 8, max = 20) @Pattern(regexp = "[0-9]{8,20}") String matriculaNo,
			boolean financialDebt) {
		super(name, surname, personCode, user);
		this.matriculaNo = matriculaNo;
		this.financialDebt = financialDebt;
	}
	
	
	public void addDebtCourse(Course course) {
		if(!debtCourses.contains(course)) {
			debtCourses.add(course);
		}
	}
	
	public void removeDebtCourse(Course course) {
		if(debtCourses.contains(course)) {
			debtCourses.remove(course);
		}
	}

}

