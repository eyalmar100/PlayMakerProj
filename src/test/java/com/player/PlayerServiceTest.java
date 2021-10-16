package com.player;

 

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.file.Files;

//import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;
//import org.springframework.util.ResourceUtils;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.player.contract.PlayerContract;
import com.player.exception.InvalidPlayerException;
import com.player.services.PlayersServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerServiceTest {
	
	@Autowired
	private PlayersServiceImpl playerService;
	 
	
	static ObjectMapper objectMapper;
	
	 
	@Test
	public void getTopFirstPlayer_Success() throws InvalidPlayerException, IOException {
		  
		File file = ResourceUtils.getFile("classpath:test.json");
		objectMapper = new ObjectMapper();
		PlayerContract playerContract = objectMapper.readValue(file, PlayerContract.class);
		String jsonResponse = playerService.getMostParticipantPlayers(playerContract);
		assertTrue(jsonResponse.contentEquals("for N=1: mostParticipatedPlayers:[Shalom]"));

	}
	
	@Test 
 	public void getTopFirstPlayer_whenExceptionIsThrown() throws JsonMappingException, JsonProcessingException {
		String json =   "{}";
		objectMapper=new ObjectMapper();
		PlayerContract playerContract = objectMapper.readValue(json, PlayerContract.class);
		Assertions.assertThrows(InvalidPlayerException.class, () -> {
			playerService.getMostParticipantPlayers(playerContract);
	
		  });
		 
	}
	

}
