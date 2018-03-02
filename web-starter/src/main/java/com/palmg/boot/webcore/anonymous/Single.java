package com.palmg.boot.webcore.anonymous;

public interface Single<T,E> {
	T of(E params);
}
