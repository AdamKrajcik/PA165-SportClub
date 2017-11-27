package cz.muni.fi.pa165.sportsclub.facade;

import cz.muni.fi.pa165.sportsclub.dto.PlayerDto;

import java.util.List;

/**
 * Facade for player
 *
 * @author 422636 Adam Krajcik
 */
public interface PlayerFacade {

    void createPlayer(PlayerDto player);

    void updatePlayer(PlayerDto player);

    void deletePlayer(PlayerDto player);

    PlayerDto getPlayer(Long id);

    PlayerDto getPlayerByEmail(String email);

    List<PlayerDto> getAllPlayers();
}
