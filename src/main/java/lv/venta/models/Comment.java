package lv.venta.models;
import java.util.Collection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.venta.models.users.AcademicPersonel;
import lv.venta.models.users.MeetingMember;
import lv.venta.models.users.Student;

@Table(name = "comment_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
	
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idc")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idc;
	
	
	@NotNull
	@Column(name="text")
	@Size(min=5,max=500)
	private String text;
	
	@ManyToOne
    @JoinColumn(name = "Idmm")
    private MeetingMember meetingMember;
	
	@ManyToOne
    @JoinColumn(name = "Idta")
    private ThesisApplication thesisApplication;
	public Comment(@NotNull @Size(min = 5, max = 500) String text, MeetingMember meetingMember,
			ThesisApplication thesisApplication) {
		super();
		this.text = text;
		this.meetingMember = meetingMember;
		this.thesisApplication = thesisApplication;
	}
	
}
