package cz.muni.fi.pa165.sportsclub.facade;

import cz.muni.fi.pa165.sportsclub.EntityFactory;
import cz.muni.fi.pa165.sportsclub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsclub.entity.Player;
import cz.muni.fi.pa165.sportsclub.mapper.MappingService;
import cz.muni.fi.pa165.sportsclub.service.PlayerService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author 422636 Adam Krajcik
 */
public class PlayerFacadeImplTest {

    @Mock
    private PlayerService playerService;

    @Mock
    private MappingService mappingService;

    @InjectMocks
    private PlayerFacade playerFacade = new PlayerFacadeImpl();

    private Player player;

    private PlayerDto playerDto;

    @BeforeClass
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() throws Exception {
        playerDto = EntityFactory.createPlayerDto();
        player = EntityFactory.createPlayer();
        player.setId(1L);

        when(mappingService.mapTo(player, PlayerDto.class)).thenReturn(playerDto);
        when(mappingService.mapTo(playerDto, Player.class)).thenReturn(player);
        when(mappingService.mapTo(Collections.singletonList(player), PlayerDto.class))
                .thenReturn(Collections.singletonList(playerDto));
    }

    @Test
    public void testCreatePlayer() throws Exception {
        playerFacade.createPlayer(playerDto);
        verify(playerService).createPlayer(player);
    }

    @Test
    public void testUpdatePlayer() throws Exception {
        playerFacade.updatePlayer(playerDto);
        verify(playerService).updatePlayer(player);
    }

    @Test
    public void testDeletePlayer() throws Exception {
        playerDto.setId(1L);
        when(playerService.findById(player.getId())).thenReturn(player);
        playerFacade.deletePlayer(playerDto);
        verify(playerService).deletePlayer(player.getId());
    }

    @Test
    public void testGetPlayer() throws Exception {
        when(playerService.findById(player.getId())).thenReturn(player);
        playerFacade.getPlayer(player.getId());
        verify(playerService).findById(player.getId());
    }

    @Test
    public void testGetPlayerByEmail() throws Exception {
        when(playerService.findByEmail(player.getEmail())).thenReturn(player);
        playerFacade.getPlayerByEmail(player.getEmail());
        verify(playerService).findByEmail(player.getEmail());
    }

    @Test
    public void testGetAllPlayers() throws Exception {
        when(playerService.getAll()).thenReturn(Collections.singletonList(player));
        List all = playerFacade.getAllPlayers();
        Assert.assertEquals(all.size(), 1);
        verify(playerService).getAll();
    }

}