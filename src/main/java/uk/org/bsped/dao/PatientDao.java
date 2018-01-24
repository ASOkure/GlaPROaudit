package uk.org.bsped.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import uk.org.bsped.model.Patient;

@Transactional
public interface PatientDao extends CrudRepository<Patient, Integer> {

}