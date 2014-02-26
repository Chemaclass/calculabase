package com.chemaclass.conversorbase.base;

import com.chemaclass.conversorbase.MainActivity;
import com.chemaclass.conversorbase.Utils;

public class Hexadecimal implements Base {

	@Override
	public String toBinary(String input) {
		if (!Utils.isHexadecimal(input) || input.length() == 0) {
			String text = "Input Error. Is Hexadecimal?";
			return text;
		}
		String resultado = "", binario = "", out = "/ ";
		for (int i = 0; i < input.length(); i++) {
			String s = "" + input.charAt(i);
			// Si no es una letra, obtenemos su binario
			if (!Utils.isAlpha(s)) {
				binario = Utils.getBinaryByDecimal(s, 16);
			}
			// Si es una letra obtenemos su binario
			else {
				binario = Utils.getBinaryByAlpha(s);
			}
			out += s + ":" + binario + " / "; // guardamos para la consola
			resultado += binario;// guardamos al resultado output
		}
		MainActivity.log(out);// imprimimos en la consola
		return resultado;// devolvemos el resultado al output
	}

	@Override
	public String toOctal(String input) {
		if (!Utils.isHexadecimal(input) || input.length() == 0) {
			return "Input Error. Is Hexadecimal?";
		}
		String resultado = "", decimal = "", out = "First convert to binary:\n/ ";
		for (int i = 0; i < input.length(); i++) {
			String s = "" + input.charAt(i);
			// Si no es una letra, obtenemos su binario
			if (!Utils.isAlpha(s)) {
				decimal = Utils.getBinaryByDecimal(s, 16);
			}
			// Si es una letra obtenemos su binario
			else {
				decimal = Utils.getBinaryByAlpha(s);
			}
			out += s + ":" + decimal + " / "; // guardamos para la consola
			resultado += decimal;// guardamos al resultado output
		}
		out += "\nThe binary would be " + resultado + "\n";
		out += "Now we do the splits in the binary result:";

		MainActivity.log(out);// imprimimos en la consola

		Binary binary = new Binary();
		resultado = binary.toOctal(resultado);

		return resultado;// devolvemos el resultado al output
	}

	@Override
	public String toDecimal(String input) {
		if (!Utils.isHexadecimal(input) || input.length() == 0) {
			return "Input Error. Is Hexadecimal?";
		}
		String resultado = "", decimal = "", out = "First convert to binary:\n/ ";
		for (int i = 0; i < input.length(); i++) {
			String s = "" + input.charAt(i);
			// Si no es una letra, obtenemos su binario
			if (!Utils.isAlpha(s)) {
				decimal = Utils.getBinaryByDecimal(s, 16);
			}
			// Si es una letra obtenemos su binario
			else {
				decimal = Utils.getBinaryByAlpha(s);
			}
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
		return (!Utils.isHexadecimal(input) || input.length() == 0) ? "Input error. Is hexadecimal?"
				: input;
	}

	@Override
	public String me() {
		return "Hexadecimal";
	}

}
