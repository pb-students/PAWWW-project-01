package net.wvffle.java.pawww.pawwwproject.db.category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Category findCategoryById (Integer id);
}
