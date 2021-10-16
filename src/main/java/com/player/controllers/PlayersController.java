package com.player.controllers;

import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.player.contract.PlayerContract;
import com.player.exception.InvalidPlayerException;
import com.player.services.PlayerService;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
 

@RestController
@RequestMapping("/api/V1")
@Slf4j
public class PlayersController {

	@Autowired
	private PlayerService playerService;

 	
	@ApiMethod
	@RequestMapping(value = "/topNplayers", method = RequestMethod.POST)	 
	public String getTopParticipantsPlayers(@RequestHeader HttpHeaders headers,@RequestBody PlayerContract playersContract) throws InvalidPlayerException {
	   
		log.info("headers valus :{} ",headers.toString());
		return playerService.getMostParticipantPlayers(playersContract);
	}
}