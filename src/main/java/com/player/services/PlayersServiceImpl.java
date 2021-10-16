package com.player.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.player.ApiResponse;
import com.player.contract.PlayerContract;
import com.player.domain.Player;
import com.player.exception.InvalidPlayerException;

import lombok.extern.slf4j.Slf4j;
 

@Service
@Slf4j
public class PlayersServiceImpl implements PlayerService {
	private Map<String, Player> playersTableMap;
	private PlayerContract playerContract;

	private ApiResponse requiredTopNPlayers() {
		ApiResponse apiResponse = new ApiResponse();
		ArrayList<String> mostParticipatedPlayers = new ArrayList<String>();
		apiResponse.setRequiredTopPlayers(playerContract.getRequiredTopPlayers());
		int i = 0;
		for (Entry<String, Player> en : playersTableMap.entrySet()) {
			if (++i <= playerContract.getRequiredTopPlayers()) {
				mostParticipatedPlayers.add(en.getKey());
			}

		}
		apiResponse.setMostParticipatedPlayer(mostParticipatedPlayers);
		return apiResponse;
	}

	@Override
	public String getMostParticipantPlayers(PlayerContract playersContract) throws InvalidPlayerException {
		this.playerContract = playersContract;
		validateContract(playersContract);		
		this.playersTableMap = new HashMap<>();
		populatePlayersTable(playersContract);
		sortByValueMap();
		ApiResponse apiResponse = new ApiResponse();
		apiResponse = requiredTopNPlayers();
		return apiResponse.toString();
	}

	private void validateContract(PlayerContract playersContract) throws InvalidPlayerException {
		if(playersContract.getRequiredTopPlayers()==null) {
			String message="RequiredTopPlayers field is missing";
			log.error("error during process :{}",message);
			throw new InvalidPlayerException(message);
	
		}
		if(playersContract.getRequiredTopPlayers()<=0) {
			String message="RequiredTopPlayers field value not valid , number must be bigger than zero";
			log.error("error during process :RequiredTopPlayers {}",message);
			throw new InvalidPlayerException(message);
	
		}
		if(playersContract.getParticipatedPlayers()==null) {
			String message="ParticipatedPlayers field is missing";
			log.error("error during process :{}",message);
			throw new InvalidPlayerException(message);
	
		}
			
	}

	private void populatePlayersTable(PlayerContract playersContract) {
		List<List<Player>> playersList = playersContract.getParticipatedPlayers();
		for (int i = 0; i < playersList.size(); i++) {
			List<Player> players = playersList.get(i);
			for (int j = 0; j < players.size(); j++) {
				Player currentPlayerInList = players.get(j);
				Player currentPlayerInMap = playersTableMap.get(currentPlayerInList.getName());
				if (currentPlayerInMap == null) {
					currentPlayerInList.setNumOfParticipantsGames(1);
					playersTableMap.put(currentPlayerInList.getName(), currentPlayerInList);
				} else {
					currentPlayerInMap.setNumOfParticipantsGames(currentPlayerInMap.getNumOfParticipantsGames() + 1);
				}
			}
		}
	}

	private void sortByValueMap() {
		playersTableMap = playersTableMap.entrySet().stream().sorted(Collections.reverseOrder(Entry.comparingByValue()))
				.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue(),
						(entry1, entry2) -> entry2, LinkedHashMap::new));
	}
	 

}
