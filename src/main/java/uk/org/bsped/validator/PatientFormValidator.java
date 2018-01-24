package uk.org.bsped.validator;

import java.math.BigDecimal;
import java.util.Calendar;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import uk.org.bsped.model.Patient;

@Component
public class PatientFormValidator
  implements Validator
{
  public boolean supports(Class<?> clazz)
  {
    return Patient.class.equals(clazz);
  }
  
  public void validate(Object target, Errors errors)
  {
    Patient patient = (Patient)target;
    
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "postcode", "NotEmpty.patientForm.postcode");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "diagnosticCategory", "NotEmpty.patientForm.diagnosticCategory");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", "NotEmpty.patientForm.sex");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthYear", "NotEmpty.patientForm.birthYear");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthMonth", "NotEmpty.patientForm.birthMonth");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ghStartYear", "NotEmpty.patientForm.ghStartYear");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ghStartMonth", "NotEmpty.patientForm.ghStartMonth");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dose", "NotEmpty.patientForm.dose");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weight", "NotEmpty.patientForm.weight");
    
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "NotEmpty.patientForm.age");
    
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "injectionsPerWeek", "NotEmpty.patientForm.injectionsPerWeek");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ongoingPrescription", "NotEmpty.patientForm.ongoingPrescription");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ghPreparation", "NotEmpty.patientForm.ghPreparation");
    if (patient.getPostcode() == null) {
      errors.rejectValue("postcode", "Pattern.patientForm.postcode");
    }
    int year = Calendar.getInstance().get(1);
    if ((patient.getBirthYear() == null) || (patient.getBirthYear().intValue() < 1950) || (patient.getBirthYear().intValue() > year)) {
      errors.rejectValue("birthYear", "Range.patientForm.birthYear");
    }
    if ((patient.getBirthMonth() == null) || (patient.getBirthMonth().intValue() < 1) || (patient.getBirthMonth().intValue() > 12)) {
      errors.rejectValue("birthMonth", "Range.patientForm.birthMonth");
    }
    if ((patient.getGhStartYear() == null) || (patient.getGhStartYear().intValue() < 1950) || (patient.getGhStartYear().intValue() > year)) {
      errors.rejectValue("ghStartYear", "Range.patientForm.ghStartYear");
    }
    if ((patient.getGhStartMonth() == null) || (patient.getGhStartMonth().intValue() < 1) || (patient.getGhStartMonth().intValue() > 12)) {
      errors.rejectValue("ghStartMonth", "Range.patientForm.ghStartMonth");
    }
    if ((patient.getGhStartYear().intValue() < patient.getBirthYear().intValue()) || ((patient.getGhStartYear() == patient.getBirthYear()) && (patient.getGhStartMonth().intValue() < patient.getBirthMonth().intValue()))) {
      errors.rejectValue("ghStartYear", "Compare.patientForm.ghStart.vs.birth");
    }
    if ((patient.getDose() == null) || (BigDecimal.ZERO.compareTo(patient.getDose()) > 0)) {
      errors.rejectValue("dose", "Range.patientForm.dose");
    }
    if ((patient.getWeight() == null) || (BigDecimal.ZERO.compareTo(patient.getWeight()) > 0)) {
      errors.rejectValue("weight", "Range.patientForm.weight");
    }
    if ((patient.getAge() == null) || (BigDecimal.ZERO.compareTo(patient.getAge()) > 0)) {
      errors.rejectValue("age", "Range.patientForm.age");
    }
    if ((patient.getInjectionsPerWeek() == null) || (patient.getInjectionsPerWeek().intValue() < 0) || (patient.getInjectionsPerWeek().intValue() > 20)) {
      errors.rejectValue("injectionsPerWeek", "Range.patientForm.injectionsPerWeek");
    }
  }
}
