package com.palmg.boot.webcore.anonymous;

public class Anony {
	public static <T> T of(NoneParams<T> s) {
		return s.of();
	}
}
