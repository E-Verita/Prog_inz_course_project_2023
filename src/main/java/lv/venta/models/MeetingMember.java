package lv.venta.models;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.venta.models.users.AcademicPersonel;

@Table(name = "meeting_member")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MeetingMember {
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idmm")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idpm;
	
	@ManyToOne
	@JoinColumn(name="Idm")
    private ITFBoardMeeting meeting;

    @ManyToOne
	@JoinColumn(name="Ida")
    private AcademicPersonel participant;
    
    @OneToMany(mappedBy = "meetingMember")
    private Collection<Comment> comments = new ArrayList<>();

	public MeetingMember(ITFBoardMeeting meeting, AcademicPersonel participant) {
		super();
		this.meeting = meeting;
		this.participant = participant;
	}
    
    
}
