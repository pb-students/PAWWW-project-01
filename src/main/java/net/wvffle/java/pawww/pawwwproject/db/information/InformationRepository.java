package net.wvffle.java.pawww.pawwwproject.db.information;

import org.springframework.data.repository.CrudRepository;

public interface InformationRepository extends CrudRepository<Information, Integer> {
    Information findInformationById (Integer id);
}
