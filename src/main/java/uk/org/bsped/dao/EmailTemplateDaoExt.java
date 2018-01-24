package uk.org.bsped.dao;

import uk.org.bsped.model.EmailTemplate;

public interface EmailTemplateDaoExt {

    EmailTemplate findEmailTemplateByName(String name);
}
