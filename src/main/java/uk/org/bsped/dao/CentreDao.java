package uk.org.bsped.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import uk.org.bsped.model.Centre;

@Transactional
public interface CentreDao extends CrudRepository<Centre, Integer> {

}