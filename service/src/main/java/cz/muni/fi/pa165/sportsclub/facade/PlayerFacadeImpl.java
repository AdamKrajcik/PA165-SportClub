package cz.muni.fi.pa165.sportsclub.facade;

import cz.muni.fi.pa165.sportsclub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsclub.entity.Player;
import cz.muni.fi.pa165.sportsclub.mapper.MappingService;
import cz.muni.fi.pa165.sportsclub.service.PlayerService;
import cz.muni.fi.pa165.sportsclub.service.RosterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class PlayerFacadeImpl implements PlayerFacade {

    @Inject
    private PlayerService playerService;

    @Inject
    private MappingService mappingService;

    @Inject
    private RosterService rosterService;


    @Override
    public void createPlayer(PlayerDto player) {
        playerService.createPlayer(mappingService.mapTo(player, Player.class));
    }

    @Override
    public void updatePlayer(PlayerDto player) {
        playerService.updatePlayer(mappingService.mapTo(player, Player.class));
    }

    @Override
    public void deletePlayer(PlayerDto player) {
        playerService.findById(player.getId()).getRosterEntries().stream().forEach(entry -> rosterService.deleteEntry(entry.getId()));
        playerService.deletePlayer(player.getId());
    }

    @Override
    public PlayerDto getPlayer(Long id) {
        return mappingService.mapTo(playerService.findById(id), PlayerDto.class);
    }

    @Override
    public PlayerDto getPlayerByEmail(String email) {
        return mappingService.mapTo(playerService.findByEmail(email), PlayerDto.class);
    }

    @Override
    public List<PlayerDto> getAllPlayers() {
        return mappingService.mapTo(playerService.getAll(), PlayerDto.class);
    }
}
