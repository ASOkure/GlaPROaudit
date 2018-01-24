package uk.org.bsped.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import uk.org.bsped.model.Users;

@Transactional
public interface UserDao extends CrudRepository<Users, Integer>, UserDaoExt {

}