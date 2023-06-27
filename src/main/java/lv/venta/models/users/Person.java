package lv.venta.models.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="person_table")
@Entity
@NoArgsConstructor
@Data
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Person {
	@Setter(value=AccessLevel.NONE)
	@Column(name="Idp")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idp;
	
	@Column(name="Name")
	@Pattern(regexp="[A-ZĀČĒĪĶĻŅŠŪŽ]{1}[a-zāčēīķļņšūž\\ ]+", message="Pirmajam burtam jābūt lielajam")
	@NotNull(message="Field cannot be empty")
	@Size(min = 3, max = 15)
	private String name;
	
	@NotNull(message="Field cannot be empty")
	@Size(min = 3, max = 15)
	@Pattern(regexp="[A-ZĀČĒĪĶĻŅŠŪŽ]{1}[a-zāčēīķļņšūž\\ ]+", message="Pirmajam burtam jābūt lielajam")
	@Column(name="Surname")
	private String surname;

	//TODO: RISINAJUMS ĀRZEMJU PERSONAS KODIEM, jaunajiem LV studentiem
	@NotNull(message="Field cannot be empty")
	@Size(min = 12, max = 12, message="Personal code is 12 characters: 123456-78910")
	@Pattern(regexp="[0-9]{6}-[0-9]{5}", message="Neatbilstošs personas kods!")
	@Column(name="PersonCode")	
	private String personCode;
	
	@OneToOne
	@JoinColumn(name="Idu")
	private User user;

	public Person(
			@Pattern(regexp = "[A-ZĀČĒĪĶĻŅŠŪŽ]{1}[a-zāčēīķļņšūž\\ ]+", message = "Pirmajam burtam jābūt lielajam") @NotNull @Size(min = 3, max = 15) String name,
			@NotNull @Size(min = 3, max = 15) @Pattern(regexp = "[A-ZĀČĒĪĶĻŅŠŪŽ]{1}[a-zāčēīķļņšūž\\ ]+", message = "Pirmajam burtam jābūt lielajam") String surname,
			@NotNull @Size(min = 12, max = 12) @Pattern(regexp = "[0-9]{6}-[0-9]{5}", message = "Neatbilstošs personas kods!") String personCode,
			User user) {
		this.name = name;
		this.surname = surname;
		this.personCode = personCode;
		this.user = user;
	}

	
	
	
}