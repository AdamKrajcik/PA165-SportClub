package cz.muni.fi.pa165.sportsclub.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * author skrovina
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, Authentication authentication, UriComponentsBuilder uriBuilder) {
        boolean authenticated = authentication != null &&
                authentication.isAuthenticated() &&
                // when Anonymous Authentication is enabled
                !(authentication
                        instanceof AnonymousAuthenticationToken);
        if (authenticated) {
            return "redirect:" + uriBuilder.path("/coach/list").toUriString();
        } else {
            return "redirect:" + uriBuilder.path("/login").toUriString();
        }
    }
}
