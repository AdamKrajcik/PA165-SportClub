package cz.muni.fi.pa165.sportsclub.controllers;

import cz.muni.fi.pa165.sportsclub.dto.CoachDto;
import cz.muni.fi.pa165.sportsclub.facade.CoachFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 *
 * @author 445403 Kristian Katanik
 */

@Controller
@RequestMapping("/coach")
public class CoachController {

    @Inject
    CoachFacade coachFacade;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getCoaches(Model model) {
        model.addAttribute("coaches", coachFacade.getAllCoaches());
        return "coach/list";
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createCoach(Model model) {
        model.addAttribute("coachCreate", new CoachDto());
        return "coach/create";
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createCoach(@ModelAttribute("coach") CoachDto coachDto, UriComponentsBuilder uriBuilder) {
        coachFacade.createCoach(coachDto);
        return "redirect:" + uriBuilder.path("/coach/list").toUriString();
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteCoach(@PathVariable long id, UriComponentsBuilder uriBuilder) {
        coachFacade.deleteCoach(id);
        return "redirect:" + uriBuilder.path("/coach/list").toUriString();
    }


    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateCoach(@PathVariable long id, Model model) {
        CoachDto coachDto = coachFacade.getCoach(id);
        if (coachDto == null)
            return "redirect:/coach";
        model.addAttribute("coach", coachDto);
        return "coach/update";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateCoach(@Valid @ModelAttribute("coach") CoachDto coachDto, @PathVariable long id, BindingResult bindingResult, Model model, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {

            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "coach/update";

        }
        CoachDto coachFromDb = coachFacade.getCoach(id);
        coachFromDb.setFirstName(coachDto.getFirstName());
        coachFromDb.setLastName(coachDto.getLastName());

        coachFacade.updateCoach(coachFromDb);
        return "redirect:" + uriBuilder.path("/coach/list").toUriString();
    }

    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String getCoach(@PathVariable("id") long id, Model model) {
        CoachDto coachDto = coachFacade.getCoach(id);
        model.addAttribute("coach", coachDto);
        return "coach/view";
    }


}