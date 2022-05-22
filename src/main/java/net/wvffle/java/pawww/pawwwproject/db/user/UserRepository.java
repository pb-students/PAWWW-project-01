package net.wvffle.java.pawww.pawwwproject.db.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername (String username);
    boolean existsByUsername (String username);
}
