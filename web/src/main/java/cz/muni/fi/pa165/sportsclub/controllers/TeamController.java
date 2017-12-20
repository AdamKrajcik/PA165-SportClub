package cz.muni.fi.pa165.sportsclub.controllers;

import cz.muni.fi.pa165.sportsclub.dto.RosterEntryDto;
import cz.muni.fi.pa165.sportsclub.dto.TeamDto;
import cz.muni.fi.pa165.sportsclub.facade.TeamFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.Set;

/**
 * @author skrovina
 */
@Controller
@RequestMapping("/team")
public class TeamController {

    @Inject
    TeamFacade teamFacade;

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String getRoster(@PathVariable long id, Model model) {
        TeamDto team = teamFacade.getTeam(id);

        Set<RosterEntryDto> rosterEntries = team.getRoster();

        model.addAttribute("rosterEntries", rosterEntries);
        model.addAttribute("team", team);

        return "team/view";
    }
}
