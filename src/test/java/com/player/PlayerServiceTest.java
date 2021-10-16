package com.player;

 

import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.player.contract.PlayerContract;
import com.player.exception.InvalidPlayerException;
import com.player.services.PlayersServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerServiceTest {
	
	@Autowired
	private PlayersServiceImpl playerService;
	 
	
	private ObjectMapper objectMapper;
	
	 
	@Test
	public void getTopThreePlayer_Success() throws InvalidPlayerException, IOException {
		  
		File file = ResourceUtils.getFile("classpath:test1.json");
		objectMapper = new ObjectMapper();
		PlayerContract playerContract = objectMapper.readValue(file, PlayerContract.class);
		String jsonResponse = playerService.getMostParticipantPlayers(playerContract);
		assertTrue(jsonResponse.contentEquals("for N=2: mostParticipatedPlayers:[Ronaldo,Shalom]"));

	}
	
	@Test
	public void getTopFirstPlayer_Success() throws InvalidPlayerException, IOException {
		  
		File file = ResourceUtils.getFile("classpath:test2.json");
		objectMapper = new ObjectMapper();
		PlayerContract playerContract = objectMapper.readValue(file, PlayerContract.class);
		String jsonResponse = playerService.getMostParticipantPlayers(playerContract);
		assertTrue(jsonResponse.contentEquals("for N=1: mostParticipatedPlayers:[Ronaldo]"));

	}
	
	@Test 
 	public void getTopFirstPlayer_whenExceptionIsThrown() throws JsonMappingException, JsonProcessingException {
		String json =   "{}";
		objectMapper=new ObjectMapper();
		PlayerContract playerContract = objectMapper.readValue(json, PlayerContract.class);
		Exception exception=Assertions.assertThrows(InvalidPlayerException.class, () -> {
			playerService.getMostParticipantPlayers(playerContract);
	
		  });
		
		String expectedMessage = "RequiredTopPlayers field is missing";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
		 
	}
	

}
