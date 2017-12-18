package cz.muni.fi.pa165.sportsclub.controller;

import cz.muni.fi.pa165.sportsclub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsclub.exception.ResourceAlreadyExistsException;
import cz.muni.fi.pa165.sportsclub.exception.ResourceNotFoundException;
import cz.muni.fi.pa165.sportsclub.facade.PlayerFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author 445403 Kristian Katanik
 */

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Inject
    private PlayerFacade playerFacade;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PlayerDto> getPlayers() {
        return playerFacade.getAllPlayers();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final PlayerDto getPlayer(@PathVariable("id") long id) throws ResourceNotFoundException {
        try{
            PlayerDto playerDto = playerFacade.getPlayer(id);
            return playerDto;
        } catch (Exception ex){
            throw new ResourceNotFoundException();
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final PlayerDto getPlayerByEmail(@PathVariable("email") String email) throws ResourceNotFoundException {
        try{
            PlayerDto playerDto = playerFacade.getPlayerByEmail(email);
            return playerDto;
        } catch (Exception ex){
            throw new ResourceNotFoundException();
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final PlayerDto createPlayer(@RequestBody PlayerDto player) throws ResourceAlreadyExistsException {
        try {
            playerFacade.createPlayer(player);
            return player;
        } catch (Exception ex) {
            throw new ResourceAlreadyExistsException();
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final PlayerDto updatePlayer(@RequestBody PlayerDto player,
                                    @PathVariable("id") long id) throws ResourceAlreadyExistsException {
        try{
            player.setId(id);
            playerFacade.updatePlayer(player);
            return player;
        } catch (Exception ex) {
            throw new ResourceAlreadyExistsException();
        }

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deletePlayer(@PathVariable("id") long id) {
        try{
            PlayerDto playerDto = playerFacade.getPlayer(id);
            playerFacade.deletePlayer(playerDto);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

}
