package uk.org.bsped.web;

import lombok.extern.log4j.Log4j;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@Log4j
public class LoginController {

	  static Logger log = Logger.getLogger(LoginController.class.getName());

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
        log.debug("Getting login page, error=" + error);
        return new ModelAndView("login", "error", error);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView getLogoutPage(@RequestParam Optional<String> error) {
        log.debug("Logging out, error=" + error);
        return new ModelAndView("login", "logout", error);
    }

}