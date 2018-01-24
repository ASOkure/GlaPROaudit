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
import uk.org.bsped.dao.AuditDao;
import uk.org.bsped.dao.CentreDao;
import uk.org.bsped.model.Audit;
import uk.org.bsped.service.AuditService;

@Controller
@RequestMapping(value = "/audit")
@Log4j
public class AuditController extends WebMvcConfigurerAdapter {

	  static Logger log = Logger.getLogger(AuditController.class.getName());

    @Autowired
    private AuditDao auditDao;
    @Autowired
    private CentreDao centreDao;
    @Autowired
    private AuditService auditService;

    @RequestMapping(value = "")
    public String index() {
        return "redirect:/audit/";
    }

    @RequestMapping(value = "/")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("audits", auditDao.findAll());
        mav.setViewName("audit/list");
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewAudit(@PathVariable Integer id) {
        log.info("searching audit id: " + id);
        Audit audit = auditDao.findOne(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("audit", audit);
        mav.setViewName("audit/view");
        return mav;
    }

    @RequestMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("audit", auditDao.findOne(id));
        mav.setViewName("audit/edit");
        return mav;
    }

    @RequestMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        auditDao.delete(id);
        redirectAttributes.addFlashAttribute("message", "Audit was deleted");
        return "redirect:/audit/";
    }

    @RequestMapping("/new")
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("audit", new Audit());
        mav.addObject("newFlag", true);
        mav.setViewName("audit/edit");
        return mav;
    }

    @RequestMapping("/save")
    public String save(@ModelAttribute Audit audit, RedirectAttributes redirectAttributes) {
        auditDao.save(audit);
        redirectAttributes.addFlashAttribute("message", "Audit was saved");
        return "redirect:/audit/";
    }

    @RequestMapping("/start")
    public ModelAndView start() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("audit", new Audit());
        mav.setViewName("audit/start");
        return mav;
    }

    @RequestMapping("/send")
    public ModelAndView send(@ModelAttribute Audit audit, RedirectAttributes redirectAttributes) {
        Integer auditId = auditService.createNewAudit(audit.getName(), audit.getNote(), "jiangj");
        ModelAndView mav = new ModelAndView();
        mav.addObject("audit", auditDao.findOne(auditId));
        mav.setViewName("audit/sent");
        redirectAttributes.addFlashAttribute("message", "Audit was created. Emails were sent");
        return mav;
    }
}