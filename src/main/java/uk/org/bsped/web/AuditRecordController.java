package uk.org.bsped.web;

import lombok.extern.log4j.Log4j;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uk.org.bsped.dao.AuditRecordDao;
import uk.org.bsped.model.AuditRecord;

@Controller
@RequestMapping(value = "/auditRecord")
@Log4j
public class AuditRecordController extends WebMvcConfigurerAdapter {

	  static Logger log = Logger.getLogger(AuditRecordController.class.getName());

    @Autowired
    private AuditRecordDao auditRecordDao;

    @RequestMapping(value = "")
    public String index() {
        return "redirect:/auditRecord/";
    }

    @RequestMapping(value = "/")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("auditRecords", auditRecordDao.findAll());
        mav.setViewName("auditRecord/list");
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable Integer id) {
        log.info("searching auditRecord id: " + id);
        AuditRecord auditRecord = auditRecordDao.findOne(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("auditRecord", auditRecord);
        mav.setViewName("auditRecord/view");
        return mav;
    }

    @RequestMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("auditRecord", auditRecordDao.findOne(id));
        mav.setViewName("auditRecord/edit");
        return mav;
    }

    @RequestMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        auditRecordDao.delete(id);
        redirectAttributes.addFlashAttribute("message", "AuditRecord was deleted");
        return "redirect:/auditRecord/";
    }

    @RequestMapping("/new")
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("auditRecord", new AuditRecord());
        mav.addObject("newFlag", true);
        mav.setViewName("auditRecord/edit");
        return mav;
    }

    @RequestMapping("/save")
    public String save(@ModelAttribute AuditRecord auditRecord, RedirectAttributes redirectAttributes) {
        auditRecordDao.save(auditRecord);
        redirectAttributes.addFlashAttribute("message", "AuditRecord was saved");
        return "redirect:/auditRecord/";
    }
}