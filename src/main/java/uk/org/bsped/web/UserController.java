package uk.org.bsped.web;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
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
import uk.org.bsped.dao.UserDao;
import uk.org.bsped.model.Users;
import uk.org.bsped.security.PasswordGenerator;

import java.util.Calendar;

@Controller
@RequestMapping(value = "/user")
@Log4j
public class UserController extends WebMvcConfigurerAdapter {
	

	  static Logger log = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "")
    public String index() {
        return "redirect:/user/";
    }

    @RequestMapping(value = "/")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userDao.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable Integer id) {
        log.info("searching user id: " + id);
        Users user = userDao.findOne(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", user);
        mav.setViewName("user/view");
        return mav;
    }

    @RequestMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView();
        Users user = userDao.findOne(id);
        user.setPassword(null);
        mav.addObject("user", user);
        mav.setViewName("user/edit");
        return mav;
    }

    @RequestMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        userDao.delete(id);
        redirectAttributes.addFlashAttribute("message", "User was deleted");
        return "redirect:/user/";
    }

    @RequestMapping("/new")
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", new Users());
        mav.addObject("newFlag", true);
        mav.setViewName("user/edit");
        return mav;
    }

    @RequestMapping("/save")
    public String save(@ModelAttribute Users user, RedirectAttributes redirectAttributes) {
        if (user.getId() == null) {
            user.setRegDate(Calendar.getInstance());
            user.setLoginCounter(0);
            user.setEnabled(true);
            user.setPassword(PasswordGenerator.hashPassword(user.getPassword()));
        } else {
            if (StringUtils.isNotBlank(user.getPassword())) {
                log.info("Password input is empty");
                user.setPassword(PasswordGenerator.hashPassword(user.getPassword()));
            } else {
                Users existingUser = userDao.findOne(user.getId());
                user.setPassword(existingUser.getPassword());
            }
        }
        userDao.save(user);
        redirectAttributes.addFlashAttribute("message", "User was saved");
        return "redirect:/user/";
    }
}