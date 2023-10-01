package lv.venta.models.users;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.venta.models.security.MyAuthority;


@Table(name="user_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MyUserId")
    private int myUserId;
    
    @Column(name = "Name")
    @NotNull
    @Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\\\ ]+")
    private String name;
    
    
    @NotNull
    @Column(name = "Surname")
    @Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\\\ ]+")
    private String surname;
    
    @NotNull
    @Column(name = "Username")
    private String username;
    
    @NotNull
    @Column(name = "Password")
    private String password;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Collection<MyAuthority> authorities = new ArrayList<>();
    
    
    
    public User(String name, String surname, String password) {
            setName(name);
            setSurname(surname);
            setPassword(password);
            username = name.toLowerCase() + "." + surname.toLowerCase();
    }
    
    public void addAuthority(MyAuthority authority) {
            if(!authorities.contains(authority)) {
                    authorities.add(authority);
            }
    }
     public void removeAuthority(MyAuthority authority) {
             if(authorities.contains(authority)) {
                     authorities.remove(authority);
             }
     }
}