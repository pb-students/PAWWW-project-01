package net.wvffle.java.pawww.pawwwproject.db.information;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.wvffle.java.pawww.pawwwproject.db.category.Category;
import net.wvffle.java.pawww.pawwwproject.db.user.User;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
public @Getter @Setter @ToString @RequiredArgsConstructor class Information implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter Integer id;

    @Length(min = 3, max = 20)
    private String title;

    @Length(min = 5, max = 500)
    private String content;

    @URL
    private String link;

    @NotNull
    @Pattern(message = "Date needs to be in dd-mm-yyyy format", regexp = "((0[1-9])|([12][0-9])|(3[01]))-((0[0-9])|(1[012]))-2\\d{3}")
    private String created;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotNull
    private User author;
}
