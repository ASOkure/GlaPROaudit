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
import uk.org.bsped.dao.CentreDao;
import uk.org.bsped.model.Centre;

@Controller
//@EnableWebMvc
@RequestMapping(value = "/centre")
@Log4j
public class CentreController extends WebMvcConfigurerAdapter {

	  static Logger log = Logger.getLogger(CentreController.class.getName());

    @Autowired
    private CentreDao centreDao;

    @RequestMapping(value = "")
    public String index() {
        return "redirect:/centre/";
    }

    @RequestMapping(value = "/")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("centres", centreDao.findAll());
        mav.setViewName("centre/list");
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable Integer id) {
        log.info("searching centre id: " + id);
        Centre centre = centreDao.findOne(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("centre", centre);
        mav.setViewName("centre/view");
        return mav;
    }

    @RequestMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("centre", centreDao.findOne(id));
        mav.setViewName("centre/edit");
        return mav;
    }

    @RequestMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        centreDao.delete(id);
        redirectAttributes.addFlashAttribute("message", "Centre was deleted");
        return "redirect:/centre/";
    }

    @RequestMapping("/new")
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("centre", new Centre());
        mav.addObject("newFlag", true);
        mav.setViewName("centre/edit");
        return mav;
    }

    @RequestMapping("/save")
    public String save(@ModelAttribute Centre centre, RedirectAttributes redirectAttributes) {
        centreDao.save(centre);
        redirectAttributes.addFlashAttribute("message", "Centre was saved");
        return "redirect:/centre/";
    }
}