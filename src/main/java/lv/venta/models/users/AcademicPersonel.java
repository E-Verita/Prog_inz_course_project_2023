package lv.venta.models.users;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.venta.models.StudyProgram;
import lv.venta.models.Thesis;

@Table(name = "academic_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name="Idp", column = @Column(name="Ida"))
public class AcademicPersonel extends Person{
	
	@Column(name="Degree")
	private Degree degree;

	@OneToMany(mappedBy="supervisor")
	private Collection <Thesis> thesis;
	
	
	@ManyToMany(mappedBy="reviewers")
	private Collection <Thesis> thesisForReviews = new ArrayList<>();
	
	public void addThesisForReviews(Thesis thesis) {
		if(!thesisForReviews.contains(thesis)) {
			thesisForReviews.add(thesis);
		}
	}
	
	public void removeThesisForReviews(Thesis thesis) {
		if(thesisForReviews.contains(thesis)) {
			thesisForReviews.remove(thesis);
		}
	}

	/*
	@ManyToMany(mappedBy="participants")
	private Collection <ITFBoardMeeting> meetings = new ArrayList<>();
	
	public void addThesisForReviews(ITFBoardMeeting meeting) {
		if(!meetings.contains(meeting)) {
			meetings.add(meeting);
		}
	}
	
	public void removeThesisForReviews(ITFBoardMeeting meeting) {
		if(meetings.contains(meeting)) {
			meetings.remove(meeting);
		}
	}
	*/
	
	 @OneToMany(mappedBy = "participant")
	    private Collection<MeetingMember> meetingMembers = new ArrayList<>();
	
	@OneToOne(mappedBy="director")
	private StudyProgram studyProgram;
	
	public AcademicPersonel(
			@Pattern(regexp = "[A-ZĀČĒĪĶĻŅŠŪŽ]{1}[a-zāčēīķļņšūž\\ ]+", message = "Pirmajam burtam jābūt lielajam") @NotNull @Size(min = 3, max = 15) String name,
			@NotNull @Size(min = 3, max = 15) @Pattern(regexp = "[A-ZĀČĒĪĶĻŅŠŪŽ]{1}[a-zāčēīķļņšūž\\ ]+", message = "Pirmajam burtam jābūt lielajam") String surname,
			@NotNull @Size(min = 12, max = 12) @Pattern(regexp = "[0-9]{6}-[0-9]{5}", message = "Neatbilstošs personas kods!") String personCode,
			User user, Degree degree) {
		super(name, surname, personCode, user);
		this.degree = degree;
	}
	
	
}
