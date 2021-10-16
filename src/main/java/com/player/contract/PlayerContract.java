package com.player.contract;

import java.util.List;
import org.jsondoc.core.annotation.ApiObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.player.domain.Player;

import lombok.Data;

 
@ApiObject
@Data
public class PlayerContract {
	    @JsonProperty("requiredTopPlayers")
	    private Integer requiredTopPlayers;
	    @JsonProperty("participatedPlayers")
	    private List<List<Player>> participatedPlayers = null;
 
}
