package com.chemaclass.calculabase.base;

import com.chemaclass.calculabase.exceptions.InvalidFormatException;

/**
 * Interface Base, que define el comportamiento común de las clases que
 * controlarán las distintas bases
 * 
 * @author chemaclass
 * 
 */
public interface Base {
	/** Base binaria */
	int BINARIO = 0;
	/** Base octal */
	int OCTAL = 1;
	/** Base decimal */
	int DECIMAL = 2;
	/** Base hexadecimal */
	int HEXADECIMAL = 3;

	String toBinary(String input) throws InvalidFormatException;

	String toOctal(String input) throws InvalidFormatException;

	String toDecimal(String input) throws InvalidFormatException;

	String toHexadecimal(String input) throws InvalidFormatException;

	String me();
}
