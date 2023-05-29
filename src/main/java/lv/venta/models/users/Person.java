package lv.venta.models.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="user_table")
@Entity
@MappedSuperclass
@NoArgsConstructor
@Data
public class Person {
	@Setter(value=AccessLevel.NONE)
	@Column(name="Idp")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idp;
	
	@Column(name="Name")
	@Pattern(regexp="[A-ZĀČĒĪĶĻŅŠŪŽ]{1}[a-zāčēīķļņšūž\\ ]+", message="Pirmajam burtam jābūt lielajam")
	@NotNull
	@Size(min = 3, max = 15)
	private String name;
	
	@NotNull
	@Size(min = 3, max = 15)
	@Pattern(regexp="[A-ZĀČĒĪĶĻŅŠŪŽ]{1}[a-zāčēīķļņšūž\\ ]+", message="Pirmajam burtam jābūt lielajam")
	@Column(name="Surname")
	private String surname;

	//TODO: RISINAJUMS ĀRZEMJU PERSONAS KODIEM, jaunajiem LV studentiem
	@NotNull
	@Size(min = 12, max = 12)
	@Pattern(regexp="[0-9]{6}-[0-9]{5}", message="Neatbilstošs personas kods!")
	@Column(name="PersonCode")	
	private String personCode;
	
}
