package cu.uci.auctoritas.repository;

import cu.uci.auctoritas.domain.PersonalAuthor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PersonalAuthorRepository extends CrudRepository<PersonalAuthor, String> {
   }
