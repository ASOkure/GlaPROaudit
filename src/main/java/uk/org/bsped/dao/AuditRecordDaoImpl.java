package uk.org.bsped.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import uk.org.bsped.model.AuditRecord;
import uk.org.bsped.model.AuditRecord.AuditRecordStatus;

public class AuditRecordDaoImpl
  extends AbstractExtendDao
  implements AuditRecordDaoExt
{
  static Logger log = Logger.getLogger(AuditRecordDaoImpl.class.getName());
  @PersistenceContext
  private EntityManager entityManager;
  
  public EntityManager getEntityManager()
  {
    return this.entityManager;
  }
  
  public List<AuditRecord> findAllPendingAuditRecords()
  {
    try
    {
      Query query = createNamedQuery("findAuditRecordByStatus", Integer.valueOf(-1), Integer.valueOf(-1), new Object[] { AuditRecord.AuditRecordStatus.PENDING });
      return query.getResultList();
    }
    catch (NoResultException e)
    {
      log.error("Error finding Pending AuditRecord", e);
    }
    return null;
  }
  
  public List<AuditRecord> findAllRequestedAuditRecords()
  {
    try
    {
      Query query = createNamedQuery("findAuditRecordByStatus", Integer.valueOf(-1), Integer.valueOf(-1), new Object[] { AuditRecord.AuditRecordStatus.REQUESTED });
      return query.getResultList();
    }
    catch (NoResultException e)
    {
      log.error("Error finding Requested AuditRecord", e);
    }
    return null;
  }
}
