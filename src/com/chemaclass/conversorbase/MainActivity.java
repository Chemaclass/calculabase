package com.chemaclass.conversorbase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
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

public class MainActivity extends BaseActivity {

	protected Button btConvertir, btInvertir;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_main);
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
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Uri uri;
		Intent browserIntent;
		Intent intent;
		switch (item.getItemId()) {
		case R.id.action_web:
			uri = Uri.parse("http://www.chemaclass.com");
			browserIntent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(browserIntent);
			return true;
		case R.id.action_aboutme:
			intent = new Intent(this, AboutMeActivity.class);
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
		case R.id.action_calculator:
			intent = new Intent(this,CalculatorActivity.class);
			startActivity(intent);
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
	protected void convertir() {
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
