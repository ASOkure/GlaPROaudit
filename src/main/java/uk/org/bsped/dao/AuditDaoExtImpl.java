package uk.org.bsped.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import uk.org.bsped.model.Audit;

public class AuditDaoExtImpl
  extends AbstractExtendDao
  implements AuditDaoExt
{
  static Logger log = Logger.getLogger(AuditDaoExtImpl.class.getName());
  @PersistenceContext
  private EntityManager entityManager;
  
  public EntityManager getEntityManager()
  {
    return this.entityManager;
  }
  
  public List<Audit> findByName(String name)
  {
    try
    {
      Query query = createNamedQuery("findAuditByName", Integer.valueOf(-1), Integer.valueOf(-1), new Object[] { name });
      return query.getResultList();
    }
    catch (NoResultException e)
    {
      log.error("Error finding Audit by name: " + name, e);
    }
    return null;
  }
}

//    @Override
//    @Transient
//    public Boolean isNameExist(String name) {
//        return CollectionUtils.isNotEmpty(findByName(name));
//    }

