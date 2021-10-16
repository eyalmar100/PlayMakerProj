package com.player.domain;

import org.jsondoc.core.annotation.ApiObject;

import lombok.Data;

@ApiObject
@Data
public class Player implements Comparable<Player> {
	private String name;
	private int age;// In real app usually there are other properties like age, num-of-goals , etc
	private int numOfParticipantsGames;
	
	public Player(String name) {
		this.name=name;
	}

	@Override
	public int compareTo(Player objectToCompare) {
	 
		if(this.numOfParticipantsGames==objectToCompare.numOfParticipantsGames)
			return 0;
		else if(this.numOfParticipantsGames<objectToCompare.numOfParticipantsGames)
			return -1;
		
		return 1;
	}

}
