package lv.venta.models;

import java.time.LocalDateTime;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
	@Setter(value=AccessLevel.NONE)
	@Column(name="Idt")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idt;
	
	@Column(name="TitleLv")
	@Size(min = 3, max = 250)
	private String titleLv;
	
	@Column(name="TitleLv")
	@Size(min = 3, max = 250)
	private String titleEn;
	
	@Column(name="Tasks")
	@Size(min = 3, max = 250)
	private String tasks;
	
	//TODO servisā pie jauna objekta izveides jaizmanto Localdate.now
	@Column(name="submitDateTime")
	private LocalDateTime submitDateTime;
	
	@Column(name="statusFromSupervisor")
	private boolean statusFromSupervisor;
	
	@Column(name="accSttaus")
	private AcceptanceStatus accStatus;
	
	@Column(name="accDateTime")
	private LocalDateTime  accDateTime;
	
}
