package com.chemaclass.calculabase;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

import com.chemaclass.calculabase.base.Base;
import com.chemaclass.calculabase.base.Binary;
import com.chemaclass.calculabase.base.Decimal;
import com.chemaclass.calculabase.base.Hexadecimal;
import com.chemaclass.calculabase.base.Octal;
import com.chemaclass.calculabase.listeners.HexaOnFocusChangeListener;

public abstract class BaseActivity extends Activity {

	public enum Conversor {
		Binary, Octal, Decimal, Hexadecimal
	}

	/** Tipo de base para la salida */
	protected Conversor conversorOutput = Conversor.Binary,
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

	private void init() {

		spInput = (Spinner) findViewById(R.id.spInput);
		spOutput = (Spinner) findViewById(R.id.spOutput);
		etInput = (EditText) findViewById(R.id.etInput);
		etOutput = (EditText) findViewById(R.id.etOutput);
		etConsola = (EditText) findViewById(R.id.etConsola);

		etConsola.setFocusable(false);
		etConsola.setClickable(true);
		// Inicializar el layout y los botones de las letras hexadecimales
		initLayoutHexadecimal();
		// Preparamos los eventos de los edits
		initEditTexts();
		// Inicializamos los valores de los spinners
		initSpinners();

		btCleanInput = (Button) findViewById(R.id.btCleanInput);
		btCleanInput.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				etInput.setText(null);
			}
		});
	}

	/**
	 * Indicamos los eventos correspondientes a los EditTexts con los que
	 * trabajamos
	 */
	private void initEditTexts() {
		// Comprobamos que su base sea hexa o no. Este evento estará pendiente
		// del cambio del foco. Construiremos un objeto al que le pasaremos el
		// spinner y el linerlayout. De esta forma, cuando en el spinner
		// seleccinemos algo concreto (en este caso que la base sea HEXA)
		// mostraremos el linearlayout pasado como segundo parámetro. De lo
		// contrario siempre ocultaremos el linearlayout
		etInput.setOnFocusChangeListener(new HexaOnFocusChangeListener(this,
				spInput, layoutBtnHexadecimal));
		etOutput.setOnFocusChangeListener(new HexaOnFocusChangeListener(this,
				spOutput, layoutBtnHexadecimal));
	}

	/**
	 * Inicializamos los spinners y les damos un valor a cada elemento (Serán
	 * las bases con las que se podrán trabajar)
	 */
	private void initSpinners() {
		// Preparamos el adapter
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter.add(getResources().getString(R.string.binary)); // binario
		adapter.add(getResources().getString(R.string.octal)); // octal
		adapter.add(getResources().getString(R.string.decimal)); // decimal
		adapter.add(getResources().getString(R.string.hexadecimal)); // hexadecimal
		// añadimos el adapter a nuestros spinner y establecemos valores default
		spInput.setAdapter(adapter);
		spInput.setSelection(Base.DECIMAL);// Decimal-> 3
		spOutput.setAdapter(adapter);
		spOutput.setSelection(Base.BINARIO);// Binario-> 0

		// Establecemos nuestros listeners a los spinners
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
					// Mostramos los botones HEXA si el foco lo tiene el
					// EditText input
					if (getWindow().getCurrentFocus().equals(etInput))
						layoutBtnHexadecimal
								.setVisibility(LinearLayout.VISIBLE);
					break;
				}
				// msg("input: " + baseInput.me(), 0);
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapter) {
			}
		});
		spOutput.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapter, View view,
					int position, long id) {
				// ocultar los botones si estamos en la calculadora
				if (getWindow().getCurrentFocus().equals(etOutput)
						&& getApplicationContext() instanceof CalculatorActivity) {
					layoutBtnHexadecimal.setVisibility(LinearLayout.GONE);
				}
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
					// Si nuestra instancia se corresponde con la calculadora y
					// el foco lo tiene el segundo input
					if (getWindow().getCurrentFocus().equals(etOutput)
							&& getApplicationContext() instanceof CalculatorActivity) {
						layoutBtnHexadecimal
								.setVisibility(LinearLayout.VISIBLE);
					}
					break;
				}
				// msg("input: " + baseInput.me(), 0);
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapter) {
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
		// El foco está en el primer campo de texto
		if (getWindow().getCurrentFocus().equals(etInput)) {
			printEdit(etInput, letter);
		} else {
			printEdit(etOutput, letter);
		}
	}

	/**
	 * 
	 * @param et
	 *            EditText sobre el cual vamos a renderizar
	 * @param letter
	 *            String letra a pintar
	 */
	private void printEdit(EditText et, String letter) {
		// O el foco está en el segundo campo de texto
		String input = et.getText().toString();
		// Obtenemos la posición actual del cursor
		int start = et.getSelectionStart();
		// introducimos la letra A en la posición del cursor
		String s = input.substring(0, start) + letter + input.substring(start);
		et.setText(s);
		// colocamos el cursor delante de la letra puesta
		et.setSelection(start + 1);
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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Uri uri;
		Intent browserIntent;
		Intent intent;
		switch (item.getItemId()) {
		case R.id.action_twitter:
			uri = Uri.parse(Utils.URL_TWITTER_CHEMACLASS);
			browserIntent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(browserIntent);
			return true;
		case R.id.action_web:
			uri = Uri.parse(Utils.URL_CHEMACLASS);
			browserIntent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(browserIntent);
			return true;
		case R.id.action_aboutme:
			intent = new Intent(this, AboutMeActivity.class);
			startActivity(intent);
			return true;
		case R.id.action_more:
			uri = Uri.parse(Utils.URL_PLAY_JMVR);
			browserIntent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(browserIntent);
			return true;

		case R.id.action_share:
			// Comprobamos que haya alguna conversión hecha
			if (etOutput.getText().length() > 0
					&& etInput.getText().length() > 0
					&& etConsola.getText().length() > 0) {
				String url_app = Utils.URL_PLAY_CONVERSOR_BASE;
				String text_to_send = getTextResult()
						+ "\nby 'CalculaBase APP' " + url_app;
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT, text_to_send);
				sendIntent.setType("text/plain");
				startActivity(sendIntent);
			} else {
				String str = getResources().getString(R.string.what);
				Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT)
						.show();
			}
			return true;
		case R.id.action_calculator:
			finish();
			intent = new Intent(this, CalculatorActivity.class);
			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}

	}

	/**
	 * Pintar el resultado por pantalla
	 * @return
	 */
	protected abstract String getTextResult();

}
