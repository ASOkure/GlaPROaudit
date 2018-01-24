package uk.org.bsped.dao;

import uk.org.bsped.model.Audit;

import java.util.List;

public interface AuditDaoExt {

    List<Audit> findByName(String name);

//    Boolean isNameExist(String name);
}
