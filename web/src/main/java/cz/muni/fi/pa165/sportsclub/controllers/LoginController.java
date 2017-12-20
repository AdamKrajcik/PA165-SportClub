package cz.muni.fi.pa165.sportsclub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(Model model, UriComponentsBuilder uriBuilder) {
        return "redirect:" + uriBuilder.path("/coach/list/").toUriString();
    }

}
