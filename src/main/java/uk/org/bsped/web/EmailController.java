package uk.org.bsped.web;

import lombok.extern.log4j.Log4j;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.org.bsped.service.EmailService;

@RestController
@RequestMapping(value = "/email")

@Log4j
public class EmailController {

	  static Logger log = Logger.getLogger(EmailController.class.getName());

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "")
    public String index() {
        return "redirect:/email/";
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String viewCentre() {
        try {
          //  emailService.sendEmail("jorbear@gmail.com", "test", "content");
        } catch (MailException e) {
            log.error("Error sending emails", e);
        }
        return "Thanks";
    }
}
