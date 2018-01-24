package uk.org.bsped.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import uk.org.bsped.model.EmailTemplate;

@Transactional
public interface EmailTemplateDao extends CrudRepository<EmailTemplate, Integer>, EmailTemplateDaoExt {

}