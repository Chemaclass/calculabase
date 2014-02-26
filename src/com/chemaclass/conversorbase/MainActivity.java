package com.chemaclass.conversorbase;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.chemaclass.conversorbase.base.Base;
import com.chemaclass.conversorbase.base.Binary;
import com.chemaclass.conversorbase.base.Decimal;
import com.chemaclass.conversorbase.base.Hexadecimal;
import com.chemaclass.conversorbase.base.Octal;

public class MainActivity extends Activity {

	private enum Conversor {

		Binary, Octal, Hexadecimal, Decimal
	}

	/** Tipo de base para la salida */
	private static Conversor conversorOutput = Conversor.Binary;
	/** Base de la cual convertir */
	private Base baseInput;

	private Spinner spInput, spOutput;
	private EditText etInput;
	private EditText etOutput;
	private static EditText etConsola;
	private Button btConvertir, btInvertir;
	private LinearLayout layoutBtnHexadecimal;
	private Button btA, btB, btC, btD, btE, btF;
	private Button btCleanInput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	/**
	 * Para hacer pruebas
	 * 
	 * @deprecated
	 */
	@Deprecated
	private void test() {
		spInput.setSelection(3);// {Binario=>0, Octal=>1, Decimal=>2, Hexa=>3}
		spOutput.setSelection(2);//
		etInput.setText("1004F43D0");
	}

	private void init() {

		spInput = (Spinner) findViewById(R.id.spInput);
		spOutput = (Spinner) findViewById(R.id.spOutput);
		etInput = (EditText) findViewById(R.id.etInput);
		etOutput = (EditText) findViewById(R.id.etOutput);
		etConsola = (EditText) findViewById(R.id.etConsola);
		// etConsola.setKeyListener(null);
		btConvertir = (Button) findViewById(R.id.btConvertir);
		btInvertir = (Button) findViewById(R.id.btInvertir);
		etOutput.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				return true;
			}
		});
		etOutput.setFocusable(false);
		etConsola.setFocusable(false);
		etConsola.setClickable(true);

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

		// Preparamos el adapter
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter.add(getResources().getString(R.string.binary)); // binario
		adapter.add(getResources().getString(R.string.octal)); // octal
		adapter.add(getResources().getString(R.string.decimal)); // decimal
		adapter.add(getResources().getString(R.string.hexadecimal)); // hexadecimal
		// añadimos el adapter a nuestros spinner
		spInput.setAdapter(adapter);
		spInput.setSelection(2);// Decimal-> 3
		spOutput.setAdapter(adapter);
		spOutput.setSelection(0);// Binario-> 0

		spInput.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapter, View view,
					int position, long id) {
				// ocultar los botones
				layoutBtnHexadecimal.setVisibility(LinearLayout.GONE);
				switch (position) {
				case 0: // Binario
					baseInput = new Binary();
					break;
				case 1: // Octal
					baseInput = new Octal();
					break;
				case 2: // Decimal
					baseInput = new Decimal();
					break;
				case 3: // Hexadecimal
					baseInput = new Hexadecimal();
					layoutBtnHexadecimal.setVisibility(LinearLayout.VISIBLE);
					break;
				}
				// msg("input: " + baseInput.me(), 0);
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapter) {
			}
			// TODO Auto-generated method stub
		});
		spOutput.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapter, View view,
					int position, long id) {
				switch (position) {
				case 0: // Binario
					conversorOutput = Conversor.Binary;
					break;
				case 1: // Octal
					conversorOutput = Conversor.Octal;
					break;
				case 2: // Decimal
					conversorOutput = Conversor.Decimal;
					break;
				case 3: // Hexadecimal
					conversorOutput = Conversor.Hexadecimal;
					break;
				}
				// msg("output: " + conversorOutput.name(), 0);
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapter) {
			}
		});
		// Inicializar el layout y los botones de las letras hexadecimales
		initLayoutHexadecimal();
		btCleanInput = (Button) findViewById(R.id.btCleanInput);
		btCleanInput.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				etInput.setText(null);
			}
		});
	}

	/**
	 * Inicializar el layout y los botones de las letras
	 */
	private void initLayoutHexadecimal() {
		layoutBtnHexadecimal = (LinearLayout) findViewById(R.id.layoutBtnHexadecimal);
		btA = (Button) findViewById(R.id.btA);
		btB = (Button) findViewById(R.id.btB);
		btC = (Button) findViewById(R.id.btC);
		btD = (Button) findViewById(R.id.btD);
		btE = (Button) findViewById(R.id.btE);
		btF = (Button) findViewById(R.id.btF);
		btA.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				printLetter("A");
			}
		});
		btB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				printLetter("B");
			}
		});
		btC.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				printLetter("C");
			}
		});
		btD.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				printLetter("D");
			}
		});
		btE.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				printLetter("E");
			}
		});
		btF.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				printLetter("F");
			}
		});
	}

	/**
	 * Escribir la letra en la posición del input que esté el cursor
	 * 
	 * @param letter
	 *            letra a escribir
	 */
	private void printLetter(String letter) {
		String input = etInput.getText().toString();
		// Obtenemos la posición actual del cursor
		int start = etInput.getSelectionStart();
		// introducimos la letra A en la posición del cursor
		String s = input.substring(0, start) + letter + input.substring(start);
		etInput.setText(s);
		// colocamos el cursor delante de la letra puesta
		etInput.setSelection(start + 1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Uri uri;
		Intent browserIntent;
		switch (item.getItemId()) {
		case R.id.action_web:
			uri = Uri.parse("http://www.chemaclass.com");
			browserIntent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(browserIntent);
			return true;
		case R.id.action_aboutme:
			Intent intent = new Intent(this, AboutMeActivity.class);
			startActivity(intent);
			return true;
		case R.id.action_more:
			uri = Uri
					.parse("https://play.google.com/store/apps/developer?id=Jos%C3%A9%20Mar%C3%ADa%20Valera%20Reales");
			browserIntent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(browserIntent);
			return true;

		case R.id.action_share:
			// Comprobamos que haya alguna conversión hecha
			if (etOutput.getText().length() > 0
					&& etInput.getText().length() > 0
					&& etConsola.getText().length() > 0) {
				String url_app = "https://play.google.com/store/apps/details?id=com.chemaclass.conversorbase";
				String text_to_send = getTextResult()
						+ "\nby 'Conversor Base! APP' " + url_app;
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT, text_to_send);
				sendIntent.setType("text/plain");
				startActivity(sendIntent);
			} else {
				String str = "What conversion?";
				Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT)
						.show();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

	private String getTextResult() {
		return getResources().getString(R.string.convert) + " "
				+ etInput.getText().toString() + " "
				+ getResources().getString(R.string.in) + " " + baseInput.me()
				+ " " + getResources().getString(R.string.to) + " "
				+ conversorOutput.name() + ": " + etOutput.getText().toString();
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
	private void convertir() {
		etConsola.setText(null);
		String input = etInput.getText().toString();
		String result = "...result...";
		try {
			if (input.length() == 0) {
				msg(getResources().getString(R.string.input_error), 0);
				return;
			}
			/*
			 * log("" + getResources().getString(R.string.convert) + " " +
			 * baseInput.me() + " " + "(" + input + ") " +
			 * getResources().getString(R.string.to) + " " +
			 * conversorOutput.name() + ": " + "\n");
			 */

			switch (conversorOutput) {
			case Binary:
				result = baseInput.toBinary(input);
				break;
			case Octal:
				result = baseInput.toOctal(input);
				break;
			case Decimal:
				result = baseInput.toDecimal(input);
				break;
			case Hexadecimal:
				result = baseInput.toHexadecimal(input);
				break;
			}
			etOutput.setText(result); // resultado
			log("\n" + getTextResult()); // consola
			// log("-------END---------\n");
		} catch (NumberFormatException e) {
			log(getResources().getString(R.string.input_error));
			// Toast 0=>'corto', 1=>'largo'
			msg(getResources().getString(R.string.input_error), 0);
		}
		etInput.requestFocus();
	}

	/**
	 * Invierte input<->Output
	 */
	private void invertir() {
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
