package lv.venta.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
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

	@NotNull
	@Column(name = "TitleLv")
	@Size(min = 3, max = 250)
	private String titleLv;

	@NotNull
	@Column(name = "TitleEng")
	@Size(min = 3, max = 250)
	private String titleEn;

	@NotNull
	@Column(name = "applications")
	@Min(value = 0)
	@Max(value = 12)
	private int applications;
	

	//MTM ar Area
		@ToString.Exclude
		@NotNull 
		@ElementCollection
		@CollectionTable(name = "thesis_areas", joinColumns = @JoinColumn(name = "Idt"))
		@Column(name = "Areas")
		@Enumerated(EnumType.STRING) //EnumType.ORDINAL ja vajag attelot index
		private Collection<Area> areas;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "complexity")
	private Complexity complexity;
	
	
	@Column(name="privateNotes")
	@Size(min=0,max=500)
	private String privateNotes;
	
	@NotNull
	@Column(name="publicNotes")
	@Size(min=0,max=1000)
	private String publicNotes;
	
	@OneToOne
	@JoinColumn(name="Ids")
	private Student assignedStudent;
	
	@ManyToMany(mappedBy="appliedThesis")
	private Collection <Student> studentsApplied = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "Ida")
	private AcademicPersonel supervisor;
		
	public void addStudent(Student student) {
		if(!studentsApplied.contains(student)) {
			studentsApplied.add(student);
		}
	}
	
	public void removeStudent(Student student) {
		if(studentsApplied.contains(student)) {
			studentsApplied.remove(student);
		}
	}
	
	@ManyToMany
	@JoinTable(name = "thesis_reviewers",
	joinColumns = @JoinColumn(name = "Idt"),
	inverseJoinColumns = @JoinColumn(name = "Ida"))
	private Collection<AcademicPersonel> reviewers = new ArrayList<>();
	
	
	public void addReviewer(AcademicPersonel reviewer) {
		if(!reviewers.contains(reviewer)) {
			reviewers.add(reviewer);
		}
	}
	
	public void removeReviewer(AcademicPersonel reviewer) {
		if(reviewers.contains(reviewer)) {
			reviewers.remove(reviewer);
		}
	}
	
	@OneToMany(mappedBy="thesis")
	private Collection <ThesisApplication> thesisApplications;

	public Thesis(@NotNull @Size(min = 3, max = 250) String titleLv, @NotNull @Size(min = 3, max = 250) String titleEn,
			@NotNull Collection<Area> areas, @NotNull Complexity complexity,
			@NotNull @Size(min = 0, max = 500) String publicNotes, AcademicPersonel supervisor) {
		super();
		this.titleLv = titleLv;
		this.titleEn = titleEn;
		this.areas = areas;
		this.complexity = complexity;
		this.publicNotes = publicNotes;
		this.supervisor = supervisor;
	}

	public Thesis(@NotNull @Size(min = 3, max = 250) String titleLv, @NotNull @Size(min = 3, max = 250) String titleEn,
			@NotNull Collection<Area> areas, @NotNull Complexity complexity,
			@NotNull @Size(min = 0, max = 500) String publicNotes, AcademicPersonel supervisor,
			Collection<ThesisApplication> thesisApplications) {
		super();
		this.titleLv = titleLv;
		this.titleEn = titleEn;
		this.areas = areas;
		this.complexity = complexity;
		this.publicNotes = publicNotes;
		this.supervisor = supervisor;
		this.thesisApplications = thesisApplications;
	}

	
	
	
	
}
