package cz.muni.fi.pa165.sportsclub.controllers;

import cz.muni.fi.pa165.sportsclub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsclub.facade.PlayerFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;

/**
 * Created by ${KristianKatanik} on 19.12.2017.
 */

@Controller
@RequestMapping("/player")
public class PlayerWebController {

    @Inject
    PlayerFacade playerFacade;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getPlayers(Model model) {
        model.addAttribute("players", playerFacade.getAllPlayers());
        return "player/list";
    }

    // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createPlayer(Model model) {
        model.addAttribute("playerCreate", new PlayerDto());
        return "player/create";
    }

    //@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createPlayer(@ModelAttribute("player") PlayerDto playerDto, UriComponentsBuilder uriBuilder) {
        playerFacade.createPlayer(playerDto);
        return "redirect:" + uriBuilder.path("/player/list").toUriString();
    }

    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String getPlayer(@PathVariable("id") long id, Model model) {
        PlayerDto playerDto = playerFacade.getPlayer(id);
        model.addAttribute("player", playerDto);
        return "player/view";
    }

    // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deletePlayer(@PathVariable long id, UriComponentsBuilder uriBuilder) {
        playerFacade.deletePlayer(playerFacade.getPlayer(id));
        return "redirect:" + uriBuilder.path("/player/list").toUriString();
    }
}