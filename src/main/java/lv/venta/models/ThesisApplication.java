package lv.venta.models;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.venta.models.users.Student;

@Table(name = "thesis_applications_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ThesisApplication {
	
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idta")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idta;
	
	@OneToMany(mappedBy="thesisApplication")
    private Collection <Comment> commentsFromITFBoardMeeting;
	
	@NotNull
	@ManyToOne
    @JoinColumn(name = "Idt")
    private Thesis thesis;
	
	@NotNull
	@ManyToOne
    @JoinColumn(name = "Ids")
    private Student student;
	
	@NotNull
	@Column(name="aim")
	@Size(min=20,max=1000)
	private String aim;

	@NotNull
	@Column(name="tasks")
	@Size(min=20,max=500)
	private String tasks;
	
	@Column(name = "applicationDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime applicationDate;
	
	@Column(name = "confirmationDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime confirmationDate;
	
	@ManyToOne
    @JoinColumn(name = "Idm")
    private ITFBoardMeeting boardMeeting;
	
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private ProcessStatus status;
	
	@Column(name="doc") // lēmums
	@Size(min=5,max=500)
	private String doc;

	@Column(name="supervisorsPrivateNotes")
	@Size(min=20,max=200)
	private String supervisorsPrivateNotes;
	
	@Column(name="supervisorsNotesToStudent")
	@Size(min=20,max=200)
	private String supervisorsNotesToStudent;
	
	@Column(name="supervisorsNotesToITFBoard")
	@Size(min=20,max=200)
	private String supervisorsNotesToITFBoard;
	
	@Column(name="ITFBoardNotesToStudent")
	@Size(min=20,max=200)
	private String ITFBoardNotesToStudent;


	public ThesisApplication(Thesis thesis, Student student,  @NotNull @Size(min = 20, max = 200) String aim,
			@NotNull @Size(min = 20, max = 500) String tasks) {
		super();
		this.thesis = thesis;
		this.student = student;
		this.aim = aim;
		this.tasks = tasks;
		this.status = ProcessStatus.waiting_on_approval_by_supervisor;
		this.applicationDate = LocalDateTime.now();
	}
	
	

}
	

