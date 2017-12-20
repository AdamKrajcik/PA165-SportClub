package cz.muni.fi.pa165.sportsclub.controllers;

import cz.muni.fi.pa165.sportsclub.dto.CoachDto;
import cz.muni.fi.pa165.sportsclub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsclub.dto.RosterEntryDto;
import cz.muni.fi.pa165.sportsclub.dto.TeamDto;
import cz.muni.fi.pa165.sportsclub.enums.AgeGroup;
import cz.muni.fi.pa165.sportsclub.facade.CoachFacade;
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
    CoachFacade coachFacade;

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

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteTeam(@PathVariable long id, UriComponentsBuilder uriBuilder) {
        teamFacade.deleteTeam(teamFacade.getTeam(id));
        return "redirect:" + uriBuilder.path("/team/list").toUriString();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getTeams(Model model) {
        model.addAttribute("teams", teamFacade.getAllTeams());
        return "team/list";
    }

    @RequestMapping(value = "/create/{coachId}", method = RequestMethod.GET)
    public String createTeamOfCoachForm(@PathVariable("coachId") long coachId, Model model, UriComponentsBuilder uriBuilder) {

        model.addAttribute("team", new TeamDto());
        model.addAttribute("coachId", coachId);
        model.addAttribute("ageGroups", AgeGroup.getAllAscending()
                .stream()
                .map(ageGroup -> ageGroup.toString())
                .collect(Collectors.toList()));

        return "team/create";
    }

    @RequestMapping(value = "/create/{coachId}", method = RequestMethod.POST)
    public String doCreateTeamOfCoach(@PathVariable("coachId") long coachId, @ModelAttribute("team") TeamDto team, Model model, UriComponentsBuilder uriBuilder) {

        CoachDto coach = coachFacade.getCoach(coachId);
        team.setCoach(coach);
        coach.getTeams().add(team);
        teamFacade.createTeam(team);
        coachFacade.updateCoach(coach);

        return "redirect:" + uriBuilder.path("/coach/view/{id}").buildAndExpand(coachId).encode().toUriString();
    }
}
