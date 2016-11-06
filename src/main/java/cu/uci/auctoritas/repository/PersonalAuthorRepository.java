package cu.uci.auctoritas.repository;

import cu.uci.auctoritas.domain.PersonalAuthor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by bichos on 4/05/16.
 */
public interface PersonalAuthorRepository extends CrudRepository<PersonalAuthor, String> {
   }
