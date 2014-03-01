package com.chemaclass.conversorbase.base;

import com.chemaclass.conversorbase.exceptions.InvalidFormatException;

public interface Base {

	String toBinary(String input) throws InvalidFormatException;
	
	String toOctal(String input) throws InvalidFormatException;

	String toDecimal(String input) throws InvalidFormatException;

	String toHexadecimal(String input) throws InvalidFormatException;

	String me();
}
