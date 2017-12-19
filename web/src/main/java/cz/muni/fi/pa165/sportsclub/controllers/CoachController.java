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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getCoaches(Model model) {
        model.addAttribute("coaches", coachFacade.getAllCoaches());
        return "coach/list";
    }

    // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createCoach(Model model) {
        model.addAttribute("coachCreate", new CoachDto());
        return "coach/create";
    }

    //@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createCoach(@ModelAttribute("coach") CoachDto coachDto, UriComponentsBuilder uriBuilder) {
        coachFacade.createCoach(coachDto);
        return "redirect:" + uriBuilder.path("/coach/list").toUriString();
    }

    // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteCoach(@PathVariable long id, UriComponentsBuilder uriBuilder) {
        coachFacade.deleteCoach(id);
        return "redirect:" + uriBuilder.path("/coach/list").toUriString();
    }

    //@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateCoach(@PathVariable long id, Model model) {
        CoachDto coachDto = coachFacade.getCoach(id);
        if (coachDto == null)
            return "redirect:/coach";
        model.addAttribute("coachUpdate", coachDto);
        return "coach/update";
    }

    //@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateCoach(@ModelAttribute("coachUpdate") CoachDto coachDto, @PathVariable("id") long id, Model model, UriComponentsBuilder uriBuilder) {
        coachDto.setId(id);
        coachFacade.updateCoach(coachDto);
        return "redirect:" + uriBuilder.path("/coach/list").toUriString();
    }

    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String getCoach(@PathVariable("id") long id, Model model) {
        CoachDto coachDto = coachFacade.getCoach(id);
        model.addAttribute("coach", coachDto);
        return "coach/view";
    }

}