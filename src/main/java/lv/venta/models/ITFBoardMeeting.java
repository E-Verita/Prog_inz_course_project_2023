package lv.venta.models;

import java.time.LocalDate;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.venta.models.users.AcademicPersonel;
import lv.venta.models.users.MeetingMember;
import lv.venta.models.users.Student;

@Table(name = "meeting_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ITFBoardMeeting {
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idm")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idm;
	
	@NotNull
	@Column(name = "date")
	@Min(value = 2020, message = "Date must be greater than or equal to 2020")
	@Min(value = 2040, message = "Date must not be greater than 2040")
	private int date;
	
	/*
	@ManyToMany
	@JoinTable(name = "personel_in_meeting",
	joinColumns = @JoinColumn(name = "Idm"),
	inverseJoinColumns = @JoinColumn(name = "Ida"))
	private Collection<AcademicPersonel> participants = new ArrayList<>();
	
	
	public void addParticipant(AcademicPersonel participant) {
		if(!participants.contains(participant)) {
			participants.add(participant);
		}
	}
	
	public void removeParticipant(AcademicPersonel participant) {
		if(participants.contains(participant)) {
			participants.remove(participant);
		}
	}*/
	
	@OneToMany(mappedBy = "meeting")
    private Collection<MeetingMember> meetingMembers = new ArrayList<>();
	
	@OneToMany(mappedBy = "boardMeeting")
    private Collection<ThesisApplication> applications = new ArrayList<>();

	public ITFBoardMeeting(
			@NotNull @Min(value = 2020, message = "Date must be greater than or equal to 2020") @Min(value = 2040, message = "Date must not be greater than 2040") int startYear,
			Collection<MeetingMember> meetingMembers, Collection<ThesisApplication> applications) {
		super();
		this.date = startYear;
		this.meetingMembers = meetingMembers;
		this.applications = applications;
	}
	

}
