package com.player.services;

import com.player.contract.PlayerContract;
import com.player.exception.InvalidPlayerException;

public interface PlayerService {
	String getMostParticipantPlayers(PlayerContract playersContract) throws InvalidPlayerException;

}
