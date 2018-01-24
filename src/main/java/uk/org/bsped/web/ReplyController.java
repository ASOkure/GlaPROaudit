package uk.org.bsped.web;

import lombok.extern.log4j.Log4j;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uk.org.bsped.dao.AuditRecordDao;
import uk.org.bsped.dao.PatientDao;
import uk.org.bsped.model.AuditRecord;
import uk.org.bsped.model.Patient;
import uk.org.bsped.validator.PatientFormValidator;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static uk.org.bsped.util.Constants.COMMA;

@Controller
@RequestMapping(value = "/reply")
@Log4j
public class ReplyController extends WebMvcConfigurerAdapter {
	

	  static Logger log = Logger.getLogger(ReplyController.class.getName());
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private AuditRecordDao auditRecordDao;
    @Autowired
    private PatientFormValidator patientFormValidator;


    //Set a form validator
    @InitBinder("patient")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(patientFormValidator);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable Integer id) {
        log.info("Filling for audit Record id: " + id);
        AuditRecord auditRecord = auditRecordDao.findOne(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("auditRecord", auditRecord);
        //sam
        if( auditRecord == null)
        	System.out.print("auditRecord does not exist");
        if (auditRecord.getStatus() == AuditRecord.AuditRecordStatus.RESPONDED) {
            mav.setViewName("reply/done");
        } else {
            mav.setViewName("reply/list");
        }
        return mav;
    }

    @RequestMapping("/{id}/new")
    public ModelAndView create(@PathVariable Integer id) {
        AuditRecord auditRecord = auditRecordDao.findOne(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("patient", new Patient());
        mav.addObject("newFlag", true);
        mav.addObject("auditRecord", auditRecord);
        mav.setViewName("reply/edit");
        return mav;
    }

    @RequestMapping("/{id}/done")
    public ModelAndView done(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView();
        AuditRecord auditRecord = auditRecordDao.findOne(id);
        auditRecord.setResponseTimestamp(Calendar.getInstance());
        auditRecord.setStatus(AuditRecord.AuditRecordStatus.RESPONDED);
        auditRecordDao.save(auditRecord);
        mav.addObject("auditRecord", auditRecordDao.findOne(id));
        mav.setViewName("reply/done");
        return mav;
    }

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.GET)
    public ModelAndView viewPatient(@PathVariable Integer id) {
        log.info("Looking at reply for patient id: " + id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("patient", patientDao.findOne(id));
        mav.setViewName("reply/view");
        return mav;
    }

    @RequestMapping("/patient/{id}/edit")
    public ModelAndView edit(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView();
        Patient patient = patientDao.findOne(id);
        mav.addObject("patient", patient);
        mav.addObject("auditRecord", patient.getAuditRecord());
        mav.setViewName("reply/edit");
        return mav;
    }

    @RequestMapping("/patient/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Patient patient = patientDao.findOne(id);
        Integer auditRecordId = patient.getAuditRecord().getAuditRecordId();
        patientDao.delete(id);
        redirectAttributes.addFlashAttribute("message", "Patient was deleted");
        return "redirect:/reply/" + auditRecordId;
    }

    @RequestMapping("/{id}/save")
    public ModelAndView save(@PathVariable Integer id, @ModelAttribute @Validated Patient patient, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            log.info("Encountered validation error while saving patient with AuditRecord ID: " + id);
            ModelAndView mav = new ModelAndView();
            mav.addObject("newFlag", true);
            mav.addObject("auditRecord", auditRecordDao.findOne(id));
            mav.setViewName("reply/edit");
            return mav;
        }

        if (patient.getPatientId() == null) {
            patient.setRecordDate(Calendar.getInstance());
        }
        patient.setAuditRecord(auditRecordDao.findOne(id));
        patientDao.save(patient);
        redirectAttributes.addFlashAttribute("message", "Patient was saved");
        return new ModelAndView("redirect:/reply/" + id);
    }

    @RequestMapping(value = "/{id}/download")
    public void download(@PathVariable Integer id, HttpServletResponse response) {
        response.setContentType("text/csv");
        String csvFileName = "patients.csv";
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);
        try {
            response.getWriter().print(createCsvContent(id));
        } catch (IOException e) {
            log.error("Error writing CSV content to response", e);
        }
    }

    private String createCsvContent(Integer id) {
        StringBuilder sb = new StringBuilder();
        sb.append("Id,Postcode,Diagnostic Category,Sex,Birth Year,Birth Month,GH Start Year,GH Start Month,Dose (mg/day),Weight (kg),Height (cm)," +
                "Injection Per Week,Ongoing Prescription,GH Preparation,Free Text,Record Date\n");
        AuditRecord auditRecord = auditRecordDao.findOne(id);
        for (Patient p : auditRecord.getPatients()) {
            sb.append(p.getPatientId()).append(COMMA)
                    .append(p.getPostcode()).append(COMMA)
                    .append(p.getDiagnosticCategory()).append(COMMA)
                    .append(p.getSex()).append(COMMA)
                    .append(p.getBirthYear()).append(COMMA)
                    .append(p.getBirthMonth()).append(COMMA)
                    .append(p.getGhStartYear()).append(COMMA)
                    .append(p.getGhStartMonth()).append(COMMA)
                    .append(p.getDose()).append(COMMA)
                    .append(p.getWeight()).append(COMMA)
                    .append(p.getAge()).append(COMMA)
                    .append(p.getInjectionsPerWeek()).append(COMMA)
                    .append(p.getOngoingPrescription()).append(COMMA)
                    .append(p.getGhPreparation()).append(COMMA)
                    .append(p.getFreeText()).append(COMMA)
                    .append(p.getRecordDate() != null ? formatter.get().format(p.getRecordDate().getTime()) : "")
                    .append("\n");
        }
        return sb.toString();
    }

    private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
        }
    };
}