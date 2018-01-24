package uk.org.bsped.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import uk.org.bsped.model.Users;

public class UserDaoExtImpl
  extends AbstractExtendDao
  implements UserDaoExt
{
  static Logger log = Logger.getLogger(UserDaoExtImpl.class.getName());
  @PersistenceContext
  private EntityManager entityManager;
  
  public Users findUserByUsername(String username)
  {
    try
    {
      Query query = createNamedQuery("findUsersByUsername", Integer.valueOf(-1), Integer.valueOf(-1), new Object[] { username });
      return (Users)query.getSingleResult();
    }
    catch (NoResultException e)
    {
      log.error("Error finding users by username: " + username, e);
    }
    return null;
  }
  
  public EntityManager getEntityManager()
  {
    return this.entityManager;
  }
}
