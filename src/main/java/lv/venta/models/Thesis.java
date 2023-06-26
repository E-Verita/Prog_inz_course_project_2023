package lv.venta.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.venta.models.users.AcademicPersonel;
import lv.venta.models.users.Student;

/* pārlikt uz citu tabulu, kurai nav 
 * 
 */
@Table(name = "thesis_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Thesis {
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idt")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idt;

	@Column(name = "TitleLv")
	@Size(min = 3, max = 250)
	private String titleLv;

	@Column(name = "TitleEn")
	@Size(min = 3, max = 250)
	private String titleEn;

	@Column(name = "Tasks")
	@Size(min = 3, max = 250)
	private String tasks;

	@Column(name = "Aim")
	@Size(min = 3, max = 250)
	private String aim;

	// TODO servisā pie jauna objekta izveides jaizmanto Localdate.now
	@Column(name = "submitDateTime")
	private LocalDateTime submitDateTime;

	@Column(name = "statusFromSupervisor")
	private boolean statusFromSupervisor;

	@Column(name = "accSttaus")
	private AcceptanceStatus accStatus;

	@Column(name = "accDateTime")
	private LocalDateTime accDateTime;

	@ManyToOne
	@JoinColumn(name = "Ids")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "Ida")
	private AcademicPersonel supervisor;

	@ManyToMany
	@JoinTable(name = "thesis_reviewers", joinColumns = @JoinColumn(name = "Idt"), inverseJoinColumns = @JoinColumn(name = "Ida"))
	private Collection<AcademicPersonel> reviewers = new ArrayList<>();

	public void addReviewer(AcademicPersonel reviewer) {
		if (!reviewers.contains(reviewer)) {
			reviewers.add(reviewer);
		}
	}

	public Thesis(@Size(min = 3, max = 250) String titleLv, @Size(min = 3, max = 250) String titleEn,
			@Size(min = 3, max = 250) String tasks, @Size(min = 3, max = 250) String aim, Student student,
			AcademicPersonel supervisor) {
		super();
		this.titleLv = titleLv;
		this.titleEn = titleEn;
		this.tasks = tasks;
		this.aim = aim;
		this.student = student;
		this.supervisor = supervisor;
		this.submitDateTime = LocalDateTime.now();
		this.accStatus = AcceptanceStatus.submitted;
	}

}
