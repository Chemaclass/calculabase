package com.chemaclass.calculabase.base;

import com.chemaclass.calculabase.exceptions.InvalidFormatException;

public interface Base {

	int BINARIO = 0;
	int OCTAL = 1;
	int DECIMAL = 2;
	int HEXADECIMAL = 3;
	
	String toBinary(String input) throws InvalidFormatException;
	
	String toOctal(String input) throws InvalidFormatException;

	String toDecimal(String input) throws InvalidFormatException;

	String toHexadecimal(String input) throws InvalidFormatException;

	String me();
}
