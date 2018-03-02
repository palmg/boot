package com.palmg.boot.webcore.anonymous;

public interface Refer<T, E> {
	T of(@SuppressWarnings("unchecked") E ...params);
}
