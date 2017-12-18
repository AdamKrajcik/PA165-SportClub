package cz.muni.fi.pa165.sportsclub.controllers;

import cz.muni.fi.pa165.sportsclub.dto.CoachDto;
import cz.muni.fi.pa165.sportsclub.facade.CoachFacade;
import cz.muni.fi.pa165.sportsclub.facade.TeamFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;

/**
 * Created by ${KristianKatanik} on 18.12.2017.
 */

@Controller
@RequestMapping("/coach")
public class CoachController {

    @Inject
    CoachFacade coachFacade;

    @Inject
    TeamFacade teamFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String getCoaches(Model model) {
        model.addAttribute("coaches", coachFacade.getAllCoaches());
        return "coach/list";
    }

}