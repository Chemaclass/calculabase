package com.chemaclass.conversorbase;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
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

public abstract class BaseActivity extends Activity {

	protected enum Conversor {

		Binary, Octal, Hexadecimal, Decimal
	}

	/** Tipo de base para la salida */
	protected static Conversor conversorOutput = Conversor.Binary,
			conversorInput = Conversor.Decimal;

	/** Base de la cual convertir */
	protected Base baseInput, baseOutput;

	protected Spinner spInput, spOutput;
	protected EditText etInput;
	protected EditText etOutput;
	protected static EditText etConsola;

	protected LinearLayout layoutBtnHexadecimal;
	protected Button btA, btB, btC, btD, btE, btF;
	protected Button btCleanInput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
		
		etConsola.setFocusable(false);
		etConsola.setClickable(true);

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
					conversorInput = Conversor.Binary;
					break;
				case 1: // Octal
					baseInput = new Octal();
					conversorInput = Conversor.Octal;
					break;
				case 2: // Decimal
					baseInput = new Decimal();
					conversorInput = Conversor.Decimal;
					break;
				case 3: // Hexadecimal
					baseInput = new Hexadecimal();
					conversorInput = Conversor.Hexadecimal;
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
				// ocultar los botones
				layoutBtnHexadecimal.setVisibility(LinearLayout.GONE);
				switch (position) {
				case 0: // Binario
					baseOutput = new Binary();
					conversorOutput = Conversor.Binary;
					break;
				case 1: // Octal
					baseOutput = new Octal();
					conversorOutput = Conversor.Octal;
					break;
				case 2: // Decimal
					baseOutput = new Decimal();
					conversorOutput = Conversor.Decimal;
					break;
				case 3: // Hexadecimal
					baseOutput = new Hexadecimal();
					conversorOutput = Conversor.Hexadecimal;
					layoutBtnHexadecimal.setVisibility(LinearLayout.VISIBLE);
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

	protected abstract void convertir();
	
	protected abstract void invertir();
	
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
