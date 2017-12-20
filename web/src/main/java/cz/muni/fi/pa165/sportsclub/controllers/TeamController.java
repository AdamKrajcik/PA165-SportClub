package cz.muni.fi.pa165.sportsclub.controllers;

import cz.muni.fi.pa165.sportsclub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsclub.dto.RosterEntryDto;
import cz.muni.fi.pa165.sportsclub.dto.TeamDto;
import cz.muni.fi.pa165.sportsclub.facade.PlayerFacade;
import cz.muni.fi.pa165.sportsclub.facade.TeamFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author skrovina
 */
@Controller
@RequestMapping("/team")
public class TeamController {

    @Inject
    TeamFacade teamFacade;

    @Inject
    PlayerFacade playerFacade;

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String getRoster(@PathVariable long id, Model model) {
        TeamDto team = teamFacade.getTeam(id);

        Set<RosterEntryDto> rosterEntries = team.getRoster();

        model.addAttribute("rosterEntries", rosterEntries);
        model.addAttribute("team", team);

        return "team/view";
    }

    @RequestMapping(value = "/add-existing/{id}", method = RequestMethod.GET)
    public String addExisting(@PathVariable long id, Model model) {
        TeamDto team = teamFacade.getTeam(id);

        List<PlayerDto> allowedPlayers = teamFacade.getAllowedPlayers(team);

        List<String> inviteEmails = allowedPlayers
                .stream()
                .map(playerDto -> playerDto.getEmail())
                .collect(Collectors.toList());

        RosterEntryDto rosterEntryDto = new RosterEntryDto();
        rosterEntryDto.setPlayer(new PlayerDto());

        model.addAttribute("team", team);
        model.addAttribute("inviteEmails", inviteEmails);
        model.addAttribute("rosterEntry", rosterEntryDto);

        return "team/add-existing";
    }

    @RequestMapping(value = "/add-existing/{id}", method = RequestMethod.POST)
    public String addExistingPOST(@ModelAttribute("rosterEntry") RosterEntryDto rosterEntry, @PathVariable("id") long id, Model model, UriComponentsBuilder uriBuilder) {
        String email = rosterEntry.getPlayer().getEmail();
        int jerseyNumber = rosterEntry.getJerseyNumber();

        PlayerDto player = playerFacade.getPlayerByEmail(email);
        TeamDto team = teamFacade.getTeam(id);
        teamFacade.addPlayer(player, team, jerseyNumber);

        return "redirect:" + uriBuilder.path("/team/view/{id}").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/remove-player/{teamId}/{playerId}", method = RequestMethod.POST)
    public String removeRosterEntry(@PathVariable("teamId") long teamId, @PathVariable("playerId") long playerId, Model model, UriComponentsBuilder uriBuilder) {
        TeamDto teamDto = teamFacade.getTeam(teamId);
        PlayerDto playerDto = playerFacade.getPlayer(playerId);
        teamFacade.removePlayer(playerDto, teamDto);

        return "redirect:" + uriBuilder.path("/team/view/{id}").buildAndExpand(teamId).encode().toUriString();
    }
}
