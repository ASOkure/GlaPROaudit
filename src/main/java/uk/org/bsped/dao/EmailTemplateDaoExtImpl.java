package uk.org.bsped.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import uk.org.bsped.model.EmailTemplate;

public class EmailTemplateDaoExtImpl
  extends AbstractExtendDao
  implements EmailTemplateDaoExt
{
  static Logger log = Logger.getLogger(EmailTemplateDaoExtImpl.class.getName());
  @PersistenceContext
  private EntityManager entityManager;
  
  public EmailTemplate findEmailTemplateByName(String name)
  {
    try
    {
      Query query = createNamedQuery("findEmailTemplateByName", Integer.valueOf(-1), Integer.valueOf(-1), new Object[] { name });
      return (EmailTemplate)query.getSingleResult();
    }
    catch (NoResultException e)
    {
      log.error("Error finding email template by name: " + name, e);
    }
    return null;
  }
  
  public EntityManager getEntityManager()
  {
    return this.entityManager;
  }
}