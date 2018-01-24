package uk.org.bsped.web;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.org.bsped.service.EmailService;

@Controller
@Log4j
public class IndexController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    //    @Bean
//    public TemplateResolver getViewResolver() {
//        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
////        resolver.setViewClass(JstlView.class);
//        resolver.setPrefix("/WEB-INF/jsp/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }
//
//    @Override
//    public void configureDefaultServletHandling(
//            DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
}