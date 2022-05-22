package net.wvffle.java.pawww.pawwwproject.db.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.wvffle.java.pawww.pawwwproject.db.user.User;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

@Entity
public @Getter @Setter @ToString @RequiredArgsConstructor class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter Integer id;

    @Length(min = 3, max = 20)
    @Pattern(message = "Category can contain only lowercase letters", regexp = "[a-z]+")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return id != null && Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
