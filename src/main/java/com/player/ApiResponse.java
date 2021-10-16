package com.player;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ApiResponse {
	private Integer requiredTopPlayers;
	private List<String> mostParticipatedPlayers = new ArrayList<>();
	private String mostParticipatedPlayer;

	public Integer getRequiredTopPlayers() {
		return requiredTopPlayers;
	}

	public void setRequiredTopPlayers(Integer requiredTopPlayers) {
		this.requiredTopPlayers = requiredTopPlayers;
	}

	public List<String> getMostParticipatedPlayers() {
		return mostParticipatedPlayers;
	}

	public void setMostParticipatedPlayer(List<String> mostParticipatedPlayers) {
		this.mostParticipatedPlayers = mostParticipatedPlayers;
	}

	public void setMostParticipatedPlayer(String mostParticipatedPlayers) {
		this.mostParticipatedPlayer = mostParticipatedPlayers;
	}

	@Override
	public String toString() {
		return "for N=" + requiredTopPlayers + ": mostParticipatedPlayers:[" + String.join(",", mostParticipatedPlayers)
				+ "]";
	}

}
