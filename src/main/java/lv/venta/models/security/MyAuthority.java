package lv.venta.models.security;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lv.venta.models.users.User;

@Table(name="MyAuthority")
@Entity
public class MyAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MyAuthorityId")
	private int MyAuthorityId;
	
	@Column(name="Title")
	@NotNull
	@Pattern(regexp = "[A-Z]{3,15}")
	private String title;

	public int getMyAuthorityId() {
		return MyAuthorityId;
	}

	public void setMyAuthorityId(int myAuthorityId) {
		MyAuthorityId = myAuthorityId;
	}

	public String getTitle() {
		return title;
	}

	@ManyToMany
	@JoinTable(name="Users_Authorities",
	joinColumns = @JoinColumn(name="MyAuthorityId"),
	inverseJoinColumns = @JoinColumn(name="MyUserId"))
	private Collection <User> users = new ArrayList <>();
	
	
	
	public MyAuthority() {
		
	}
	
	public MyAuthority(String title) {
		setTitle(title);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addUser(User user) {
		if(!users.contains(user)) {
			users.add(user);
		}
	}

	public void removeUser(User user) {
		if(users.contains(user)) {
			users.remove(user);
		}
	}
 	
}
