package uk.org.bsped.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import uk.org.bsped.model.EmailLog;

public class EmailLogDaoExtImpl
  extends AbstractExtendDao
  implements EmailLogDaoExt
{
  static Logger log = Logger.getLogger(EmailLogDaoExtImpl.class.getName());
  @PersistenceContext
  private EntityManager entityManager;
  
  public EntityManager getEntityManager()
  {
    return this.entityManager;
  }
  
  public List<EmailLog> findLatestEmailLogByAuditRecordId(Integer auditRecordId)
  {
    try
    {
      Query query = createNamedQuery("findLatestEmailLogByAuditRecordId", Integer.valueOf(-1), Integer.valueOf(-1), new Object[] { auditRecordId });
      return query.getResultList();
    }
    catch (NoResultException e)
    {
      log.error("Error finding latest email log by auditRecordId: " + auditRecordId, e);
    }
    return null;
  }
  
  public List<EmailLog> findEmailLogsByAuditRecordId(Integer auditRecordId)
  {
    try
    {
      Query query = createNamedQuery("findEmailLogsByAuditRecordId", Integer.valueOf(-1), Integer.valueOf(-1), new Object[] { auditRecordId });
      return query.getResultList();
    }
    catch (NoResultException e)
    {
      log.error("Error finding emailLogs by auditRecordId: " + auditRecordId, e);
    }
    return null;
  }
  
  public List<EmailLog> findBySubject(String subject)
  {
    try
    {
      Query query = getEntityManager().createQuery("select el from EmailLog el where el.subject = ?1 order by el.timestamp desc");
      return query.setParameter(1, "TEST").getResultList();
    }
    catch (NoResultException e)
    {
      log.error("Error finding latest email log by subject: " + subject, e);
    }
    return null;
  }
}
