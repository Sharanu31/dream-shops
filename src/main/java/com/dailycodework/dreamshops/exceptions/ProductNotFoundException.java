package com.dailycodework.dreamshops.exceptions;

public class ProductNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2853494327131051236L;

	public ProductNotFoundException(String message) {
		super(message);
	}

}
