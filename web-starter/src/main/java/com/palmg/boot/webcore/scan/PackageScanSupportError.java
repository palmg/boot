package com.palmg.boot.webcore.scan;

public class PackageScanSupportError extends Exception {
	private static final long serialVersionUID = 1L;
	public PackageScanSupportError(Exception e) {
		super(e);
	}
}
