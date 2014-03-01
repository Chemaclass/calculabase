package com.chemaclass.conversorbase.exceptions;

public class InvalidFormatException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String mensaje = "Invalid format exception";
	
	public InvalidFormatException(){
		super(mensaje);
	}
	
	public InvalidFormatException(String s){
		super(s);
	}
	
}
