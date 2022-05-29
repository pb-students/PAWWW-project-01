package net.wvffle.java.pawww.pawwwproject.db.information;

import net.wvffle.java.pawww.pawwwproject.db.category.Category;
import net.wvffle.java.pawww.pawwwproject.db.user.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface InformationRepository extends CrudRepository<Information, Integer> {
    Iterable<Information> findAllByAuthor (User author, Sort sort);
    Iterable<Information> findAllByCategory (Category category);
}
