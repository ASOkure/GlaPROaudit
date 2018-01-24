package uk.org.bsped.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uk.org.bsped.dao.PatientDao;
import uk.org.bsped.model.AuditRecord;
import uk.org.bsped.model.Centre;
import uk.org.bsped.model.Patient;

@Controller
@RequestMapping({"/patient"})
public class PatientController
  extends WebMvcConfigurerAdapter
{
  static final Logger log = Logger.getLogger(PatientController.class);
  @Autowired
  private PatientDao patientDao;
  
  @RequestMapping({""})
  public String index()
  {
    return "redirect:/patient/";
  }
  
  @RequestMapping({"/"})
  public ModelAndView list()
  {
    ModelAndView mav = new ModelAndView();
    mav.addObject("patients", this.patientDao.findAll());
    mav.setViewName("patient/list");
    return mav;
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView view(@PathVariable Integer id)
  {
    log.info("searching patient id: " + id);
    Patient patient = (Patient)this.patientDao.findOne(id);
    ModelAndView mav = new ModelAndView();
    mav.addObject("patient", patient);
    mav.setViewName("patient/view");
    return mav;
  }
  
  @RequestMapping({"/{id}/edit"})
  public ModelAndView edit(@PathVariable Integer id)
  {
    ModelAndView mav = new ModelAndView();
    mav.addObject("patient", this.patientDao.findOne(id));
    mav.setViewName("patient/edit");
    return mav;
  }
  
  @RequestMapping({"/{id}/delete"})
  public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes)
  {
    this.patientDao.delete(id);
    redirectAttributes.addFlashAttribute("message", "Patient was deleted");
    return "redirect:/patient/";
  }
  
  @RequestMapping({"/new"})
  public ModelAndView create()
  {
    ModelAndView mav = new ModelAndView();
    mav.addObject("patient", new Patient());
    mav.addObject("newFlag", Boolean.valueOf(true));
    mav.setViewName("patient/edit");
    return mav;
  }
  
  @RequestMapping({"/save"})
  public String save(@ModelAttribute Patient patient, RedirectAttributes redirectAttributes)
  {
    if (patient.getPatientId() == null) {
      patient.setRecordDate(Calendar.getInstance());
    }
    this.patientDao.save(patient);
    redirectAttributes.addFlashAttribute("message", "Patient was saved");
    return "redirect:/patient/";
  }
  
  @RequestMapping({"/download"})
  public void download(HttpServletResponse response)
  {
    response.setContentType("text/csv");
    String csvFileName = "patients.csv";
    String headerKey = "Content-Disposition";
    String headerValue = String.format("attachment; filename=\"%s\"", new Object[] { csvFileName });
    response.setHeader(headerKey, headerValue);
    try
    {
      response.getWriter().print(createCsvContent());
    }
    catch (IOException e)
    {
      log.error("Error writing CSV content to response", e);
    }
  }
  
  private String createCsvContent()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("Id,Centre Name, Postcode,Diagnostic Category,Sex,Birth Year,Birth Month,GH Start Year,GH Start Month,Dose (mg/day),Weight (kg), Age,Injection Per Week,Ongoing Prescription,GH Preparation,Free Text,Record Date\n");
    for (Patient p : this.patientDao.findAll()) {
      sb.append(p.getPatientId()).append(",").append(p.getAuditRecord().getCentre().getCentreName()).append(",").append(p.getPostcode()).append(",").append(p.getDiagnosticCategory()).append(",").append(p.getSex()).append(",").append(p.getBirthYear()).append(",").append(p.getBirthMonth()).append(",").append(p.getGhStartYear()).append(",").append(p.getGhStartMonth()).append(",").append(p.getDose()).append(",").append(p.getWeight()).append(",").append(p.getAge()).append(",").append(p.getInjectionsPerWeek()).append(",").append(p.getOngoingPrescription()).append(",").append(p.getGhPreparation()).append(",").append(p.getFreeText()).append(",").append(p.getRecordDate() != null ? ((SimpleDateFormat)formatter.get()).format(p.getRecordDate().getTime()) : "").append("\n");
    }
    return sb.toString();
  }
  
  private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal()
  {
    protected SimpleDateFormat initialValue()
    {
      return new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
    }
  };
}