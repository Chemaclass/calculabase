package com.chemaclass.calculabase;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chemaclass.calculabase.base.Base;
import com.chemaclass.calculabase.exceptions.InvalidFormatException;


public class MainActivity extends BaseActivity {

	protected Button btConvertir, btInvertir;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_main);
		super.onCreate(savedInstanceState);

		init();
	}

	private void init() {

		// etConsola.setKeyListener(null);

		etOutput.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				return true;
			}
		});
		etOutput.setFocusable(false);

		btConvertir = (Button) findViewById(R.id.btConvertir);
		btInvertir = (Button) findViewById(R.id.btInvertir);

		btConvertir.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				convertir();
			}
		});
		btInvertir.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				invertir();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
	

	/**
	 * Escribe en la consola de la app
	 * 
	 * @param s
	 *            String a escribir en la consola de la app (con \n)
	 */
	public static void log(String s) {
		// Para la consola de la app
		etConsola.setText(etConsola.getText() + s + "\n");
	}

	/**
	 * Llevar a cabo la conversión entre los dos números en bases distintas
	 */
	protected void convertir() {
		etConsola.setText(null);
		String input = etInput.getText().toString();
		String result = "...result...";
		try {
			if (input.length() == 0) {
				msg(getResources().getString(R.string.input_error), 0);
				return;
			}

			try {
				result = getConversionBy(input, baseInput, conversorOutput);
			} catch (InvalidFormatException e) {
				result = e.getMessage();
			}

			etOutput.setText(result); // resultado
			log("\n" + getTextResult()); // consola
			// log("-------END---------\n");
		} catch (NumberFormatException e) {
			log(getResources().getString(R.string.input_error));
			// Toast 0=>'corto', 1=>'largo'
			msg(getResources().getString(R.string.input_error), 0);
		}
		// Devolvemos el foco al input
		etInput.requestFocus();
	}

	/**
	 * Obtener la conversión
	 * 
	 * @param input
	 *            String
	 * @param baseInput
	 *            Base "en qué base vinene"
	 * @param conversorOutput
	 *            Conversor "a qué base va"
	 * @return String con la conversión realizada y su explicación
	 * @throws InvalidFormatException
	 */
	private String getConversionBy(String input, Base baseInput,
			Conversor conversorOutput) throws InvalidFormatException {
		switch (conversorOutput) {
		case Binary:
			return baseInput.toBinary(input);
		case Octal:
			return baseInput.toOctal(input);
		case Decimal:
			return baseInput.toDecimal(input);
		case Hexadecimal:
			return baseInput.toHexadecimal(input);
		}
		return null;
	}

	/**
	 * Obtener el resultadoo a mostrar en la consola
	 * 
	 * @return string
	 */
	@Override
	protected String getTextResult() {
		return getResources().getString(R.string.convert) + " "
				+ etInput.getText().toString() + " "
				+ getResources().getString(R.string.in) + " " + baseInput.me()
				+ " " + getResources().getString(R.string.to) + " "
				+ conversorOutput.name() + ": " + etOutput.getText().toString();
	}

	/**
	 * Invierte input<->Output
	 */
	protected void invertir() {
		// Cambiamos los Spinners
		int inputSelect = spInput.getSelectedItemPosition();
		int outputSelect = spOutput.getSelectedItemPosition();
		spInput.setSelection(outputSelect);
		spOutput.setSelection(inputSelect);
		// cambiamos los textos
		String input = etInput.getText().toString();
		String output = etOutput.getText().toString();
		etInput.setText(output);
		etOutput.setText(input);
	}

	/**
	 * Muestra un Toast. Para la duración larga se le debe pasar un 1 como
	 * segundo argumento
	 * 
	 * @param text
	 *            Texto a mostrar
	 * @param duration
	 *            0:corta, 1:larga
	 */
	public void msg(String text, int duration) {
		switch (duration) {
		case 1:
			Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG)
					.show();
			break;
		default:
			Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
					.show();
		}
	}

}
