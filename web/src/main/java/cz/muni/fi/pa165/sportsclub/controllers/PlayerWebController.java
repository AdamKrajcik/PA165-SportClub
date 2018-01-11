package cz.muni.fi.pa165.sportsclub.controllers;

import cz.muni.fi.pa165.sportsclub.dto.PlayerCreateDto;
import cz.muni.fi.pa165.sportsclub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsclub.dto.PlayerUpdateDto;
import cz.muni.fi.pa165.sportsclub.facade.PlayerFacade;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
        model.addAttribute("playerCreate", new PlayerCreateDto());
        return "player/create";
    }

    //@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createPlayer(@Valid @ModelAttribute("playerCreate") PlayerCreateDto playerDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            //playerDto.setDateOfBirth(null);
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);

            }
            return "player/create";

        }
        PlayerDto player = new PlayerDto();
        player.setLastName(playerDto.getLastName());
        player.setFirstName(playerDto.getFirstName());
        player.setWeight(playerDto.getWeight());
        player.setHeight(playerDto.getHeight());
        try {
            player.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(playerDto.getDateAsString()));
        } catch (ParseException e) {
            return "player/create";
        }
        player.setEmail(playerDto.getEmail());

        try {
            playerFacade.createPlayer(player);
        } catch (JpaSystemException e) {
            redirectAttributes.addFlashAttribute("alert_danger", "Error, player with email " + playerDto.getEmail() + " already exists!");
            return "redirect:" + uriBuilder.path("/player/list").toUriString();
        }
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

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String updatePlayer(@Valid @ModelAttribute("player") PlayerUpdateDto playerUpdateDto, BindingResult bindingResult, Model model, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {

            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "player/update";

        }
        PlayerDto playerFromDb = playerFacade.getPlayer(playerUpdateDto.getId());
        playerFromDb.setHeight(playerUpdateDto.getHeight());
        playerFromDb.setWeight(playerUpdateDto.getWeight());
        playerFromDb.setFirstName(playerUpdateDto.getFirstName());
        playerFromDb.setLastName(playerUpdateDto.getLastName());

        playerFacade.updatePlayer(playerFromDb);
        return "redirect:" + uriBuilder.path("/player/list").toUriString();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updatePlayer(@PathVariable long id, Model model) {
        PlayerDto player = playerFacade.getPlayer(id);
        if (player == null)
            return "redirect:/coach";
        model.addAttribute("player", player);
        return "player/update";
    }
}