package com.chemaclass.calculabase.base;

import com.chemaclass.calculabase.MainActivity;
import com.chemaclass.calculabase.Utils;


public class Octal implements Base {

	@Override
	public String toBinary(String input) {
		if (!Utils.isOctal(input) || input.length() == 0) {
			return "Input Error. Is Octal? ";
		}
		String resultado = "", binario = "", out = "/ ";
		for (int i = 0; i < input.length(); i++) {
			String s = "" + input.charAt(i);
			binario = Utils.getBinaryByDecimal(s, 8);// obtenemos el binario
			out += s + ":" + binario + " / "; // guardamos para la consola
			resultado += binario;// guardamos al resultado output
		}
		MainActivity.log(out);// imprimimos en la consola
		return resultado;// devolvemos el resultado al output
	}


	@Override
	public String toOctal(String input) {
		return (!Utils.isOctal(input) || input.length() == 0) ? "Input error. Is octal?"
				: input;

	}

	@Override
	public String toDecimal(String input) {
		if (!Utils.isOctal(input) || input.length() == 0) {
			return "Input Error. Is Octal?";
		}
		String resultado = "", decimal = "", out = "First convert to binary:\n/ ";
		for (int i = 0; i < input.length(); i++) {
			String s = "" + input.charAt(i);
			decimal = Utils.getBinaryByDecimal(s, 8);// obtenemos el decimal
			out += s + ":" + decimal + " / "; // guardamos para la consola
			resultado += decimal;// guardamos al resultado output
		}
		out += "\nThe binary would be " + resultado + "\n";
		out += "Now we do the sums to pass from binary to decimal:";

		MainActivity.log(out);// imprimimos en la consola

		Binary binary = new Binary();
		resultado = binary.toDecimal(resultado);

		return resultado;// devolvemos el resultado al output
	}

	@Override
	public String toHexadecimal(String input) {
		if (!Utils.isOctal(input) || input.length() == 0) {
			return "Input Error. Is Octal?";
		}
		String resultado = "", decimal = "", out = "First convert to binary:\n/ ";
		for (int i = 0; i < input.length(); i++) {
			String s = "" + input.charAt(i);
			decimal = Utils.getBinaryByDecimal(s, 8);// obtenemos el decimal
			out += s + ":" + decimal + " / "; // guardamos para la consola
			resultado += decimal;// guardamos al resultado output
		}
		out += "\nThe binary would be " + resultado + "\n";
		out += "Now we do the splits in the binary result:";

		MainActivity.log(out);// imprimimos en la consola

		Binary binary = new Binary();
		resultado = binary.toHexadecimal(resultado);

		return resultado;// devolvemos el resultado al output
	}


	@Override
	public String me() {
		return "Octal";
	}
}
