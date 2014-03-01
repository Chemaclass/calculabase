package com.chemaclass.calculabase.base;

import com.chemaclass.calculabase.MainActivity;
import com.chemaclass.calculabase.Utils;

public class Decimal implements Base {

	@Override
	public String toBinary(String input) {
		return (!Utils.isDecimal(input) || input.length() == 0) ? "Input Error. Is decimal?"
				: convert(input, 2);
	}

	@Override
	public String toOctal(String input) {
		return (!Utils.isDecimal(input) || input.length() == 0) ? "Input Error. Is decimal?"
				: convert(input, 8);
	}

	@Override
	public String toDecimal(String input) {
		return (!Utils.isDecimal(input) || input.length() == 0) ? "Input Error. Is decimal?"
				: input;
	}

	@Override
	public String toHexadecimal(String input) {
		return (!Utils.isDecimal(input) || input.length() == 0) ? "Input Error. Is decimal?"
				: convert(input, 16);
	}

	/**
	 * Transformar en número de entrada (en base decimal) a la base que se
	 * indique en el parámetro base.
	 * 
	 * @param inputDecimal
	 *            Número en base decimal que queremos manipular
	 * @param base
	 *            Base a la cual queremos transformar la entrada
	 */
	private String convert(String inputDecimal, int base) {
		String resultado = "", out = "";
		long input, aux = Long.parseLong(inputDecimal);
		int residuo = 0;
		while (Math.round(aux / base) != 0) {
			residuo = (int) aux % base;// sacamos el residuo
			residuo = Math.abs(residuo); // Nos aseguramos de que sea su abs
			input = aux; // guardamos aux antes de hacer su división
			aux /= base;// dividimos
			// Si la base a convertir es hexadecimal convertimos el número en
			// letra, si no, simplemente ponemos su número
			resultado = ((base == 16 && residuo > 9) ? Utils.getCharByHexa(residuo)
					: residuo) + resultado;
			out += input + " / " + base + " =  " + aux + " (->" + residuo
					+ ")\t| ... " + resultado + "\n";
		}
		// Y una vez más para el que queda
		residuo = (int) aux % base;// sacamos el residuo
		input = aux; // guardamos aux antes de hacer su división
		aux /= base;// Oudividimos
		resultado = ((base == 16 && residuo > 9) ? Utils.getCharByHexa(residuo)
				: residuo) + resultado;
		out += input + " / " + base + "  =  " + aux + " (->" + residuo
				+ ")\t| ... " + resultado;

		MainActivity.log(out);
		return resultado; // editText de resultado
	}



	@Override
	public String me() {
		return "Decimal";
	}

}
