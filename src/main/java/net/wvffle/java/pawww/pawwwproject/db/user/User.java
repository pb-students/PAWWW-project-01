package net.wvffle.java.pawww.pawwwproject.db.user;

import lombok.*;
import net.wvffle.java.pawww.pawwwproject.annotations.PasswordMatches;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@PasswordMatches
@Table(name = "users")
public @Getter @Setter @ToString @RequiredArgsConstructor class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter Integer id;

    @Column(unique = true)
    @NotNull
    @Length(min = 3, max = 20)
    @Pattern(message = "Username can contain only lowercase letters", regexp = "[a-z]+")
    private String username;

    @NotNull
    @Length(min = 5)
    private String password;
    private String matchingPassword;

    @NotNull
    @Length(min = 3, max = 20)
    @Pattern(regexp = "[A-Z][a-z]+")
    private String firstName;

    @NotNull
    @Length(min = 3, max = 50)
    @Pattern(regexp = "[A-Z][a-z]+([- ][A-Z][a-z]+)?")
    private String lastName;

    @NotNull
    @Min(18)
    private Integer age;

    private String role = "limited_user";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
