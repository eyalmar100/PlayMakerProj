package com.player.exception;

public class InvalidJsonStructure extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidJsonStructure(String msg) {
		super(msg);
	}
}
