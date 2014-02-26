package com.chemaclass.conversorbase;

import java.util.Calendar;

public class Utils {

	public static String getBinaryByDecimal(String s) {
		return String.valueOf(Integer.parseInt(s, 10));
	}

	public static String getDecimalByBinary(String s) {
		return String.valueOf(Integer.parseInt(s, 2));
	}

	public static String getBinaryByDecimal(String input, int base) {
		String resultado = "";
		long aux = Long.parseLong(input);
		int residuo = 0;
		int bin = 2;
		while (Math.round(aux / bin) != 0) {
			residuo = (int) aux % bin;// sacamos el residuo
			aux /= bin;// dividimos
			// Si la base a convertir es hexadecimal convertimos el número en
			// letra, si no, simplemente ponemos su número
			resultado = residuo + resultado;
		}
		// Y una vez más para el que queda
		residuo = (int) aux % bin;// sacamos el residuo
		aux /= bin;// dividimos
		resultado = residuo + resultado;
		if (base == 8) {
			// Nos aseguramos que hayan de 3 y 3, para poder hacer la división
			if ((residuo = resultado.length() % 3) != 0) {
				String s = (residuo == 1) ? "00" : "0";
				resultado = s + resultado;
			}
		} else if (base == 16) {
			// Nos aseguramos que hayan de 4 y 4, para poder hacer la división
			if ((residuo = resultado.length() % 4) != 0) {
				String s = (residuo == 1) ? "000" : ((residuo == 2) ? "00" : "0");
				resultado = s + resultado;
			}
		}
		return resultado;
	}

	/**
	 * Obtener el char que se corresponde con su posición en hexadecimal
	 * 
	 * @param numHexa
	 * @return String que se corresponde con su posición en hexadecimal
	 */
	public static String getCharByHexa(int numHexa) {
		switch (numHexa) {
		case 10:
			return "A";
		case 11:
			return "B";
		case 12:
			return "C";
		case 13:
			return "D";
		case 14:
			return "E";
		case 15:
			return "F";
		default:
			return "" + numHexa;
		}
	}

	/**
	 * Si es una letra correspondiente al hexadecimal
	 * 
	 * @param c
	 *            letra
	 * @return si es letra hexa: true, si no false
	 */
	public static boolean isAlpha(String c) {
		if (c.equals("A") || c.equals("B") || c.equals("C") || c.equals("D")
				|| c.equals("E") || c.equals("F"))
			return true;
		return false;
	}

	/**
	 * Devuelve el binario de la letra hexadecimal
	 * 
	 * @param c
	 *            letra a obtener su binario
	 * @return binario de la letra pasada por parámetro
	 */
	public static String getBinaryByAlpha(String c) {
		if (c.equals("A")) {
			return "1010";
		} else if (c.equals("B")) {
			return "1011";
		} else if (c.equals("C")) {
			return "1100";
		} else if (c.equals("D")) {
			return "1101";
		} else if (c.equals("E")) {
			return "1110";
		} else if (c.equals("F")) {
			return "1111";
		} else {
			return c;
		}
	}

	/**
	 * Comprueba que el número pasado como cadena sea un número binario. Si
	 * contiene algún otro dígito que no sea 0 o 1 devolverá false
	 * 
	 * @param inputBinary
	 * @return si es un número binario:true, si no:false
	 */
	public static boolean isBinary(String inputBinary) {
		for (char c : inputBinary.toCharArray())
			if (c != '0' && c != '1')
				return false;
		return true;
	}

	/**
	 * Comprueba que el número pasado como cadena sea un número octal. Si
	 * contiene algún otro dígito que no sea 0..7 devolverá false
	 * 
	 * @param inputOctal
	 * @return si es un número octal: true, si no:false
	 */
	public static boolean isOctal(String inputOctal) {
		for (char c : inputOctal.toCharArray())
			if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4'
					&& c != '5' && c != '6' && c != '7')
				return false;
		return true;
	}

	/**
	 * Comprueba que el número pasado como cadena sea un número decimal. Si
	 * contiene algún otro dígito que no sea 0..9 devolverá false
	 * 
	 * @param inputDecimal
	 * @return si es un número decimal: true, si no:false
	 */
	public static boolean isDecimal(String inputDecimal) {
		for (char c : inputDecimal.toCharArray())
			if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4'
					&& c != '5' && c != '6' && c != '7' && c != '8' && c != '9')
				return false;
		return true;
	}

	/**
	 * Comprueba que el número pasado como cadena sea un número hexadecimal. Si
	 * contiene algún otro dígito que no sea 0..7 devolverá false
	 * 
	 * @param inputHexadecimal
	 * @return si es un número hexadecimal: true, si no:false
	 */
	public static boolean isHexadecimal(String inputHexadecimal) {
		for (char c : inputHexadecimal.toCharArray())
			if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4'
					&& c != '5' && c != '6' && c != '7' && c != '8' && c != '9'
					&& c != 'A' && c != 'B' && c != 'C' && c != 'D' && c != 'E'
					&& c != 'F')
				return false;
		return true;
	}

	/**
	 * Obtener la fecha y hora actual
	 * 
	 * @return
	 */
	public static String getFecha() {
		String s = "|| ";
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day_of_month = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		s += day_of_month + "-" + month + "-\" +" + year + " " + hour + ":"
				+ minute + ":" + second;
		s += " ||";
		return s;
	}
}
