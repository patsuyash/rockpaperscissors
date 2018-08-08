package com.rps.infrastructure;

import com.rps.application.players.PlayerServiceResponse;
import com.rps.application.players.PlayerService;
import com.rps.domain.actors.Player;
import com.rps.domain.gameplay.ActionType;
import com.rps.infrastructure.players.PlayerResponse;
import com.rps.infrastructure.players.PlayerResponseTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@RestController
public class RockPaperScissorsController {

    @Autowired
    private PlayerService playerService;

    public RockPaperScissorsController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = "application/json")
    public DefaultResponse ping() {
        return new DefaultResponse("pong", "TEST");
    }

    @RequestMapping(value = "/register/{playerName}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<PlayerResponse> registerPlayer(@PathVariable("playerName") String playerName) {
        PlayerServiceResponse playerServiceResponse = playerService.createPlayerWithName(playerName);
        return ResponseEntity.ok(PlayerResponseTranslator.translate(playerServiceResponse));
    }

    @RequestMapping(value = "/getplayer/{playername}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<PlayerResponse> getPlayer(@PathVariable("playername") String playerName) {
        PlayerServiceResponse playerServiceResponse = playerService.getPlayer(playerName);
        return ResponseEntity.ok(PlayerResponseTranslator.translate(playerServiceResponse));
    }

    @RequestMapping(value = "/readyplayer/{playername}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<PlayerResponse> ready(@PathVariable("playername") String playerName) {
        PlayerServiceResponse playerServiceResponse = playerService.changePlayerState(playerName, Player.State.READY);
        return ResponseEntity.ok(PlayerResponseTranslator.translate(playerServiceResponse));
    }

    @RequestMapping(value = "/actionType/{playerid}/{actionType}", method = RequestMethod.GET, produces = "application/json")
    public Response play(@PathVariable("playerid") long playerId, @PathVariable("actionType") ActionType actionType) {
        throw new NotImplementedException();
    }

}