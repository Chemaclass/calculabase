package com.chemaclass.conversorbase;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.chemaclass.conversorbase.exceptions.InvalidFormatException;

public class CalculatorActivity extends BaseActivity {

	enum TipoOperacion {
		sum, subtraction, multiplication, division;
	}

	protected Button btSumar, btRestar, btMultiplicar, btDividir;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_calculator);
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {

		btSumar = (Button) findViewById(R.id.btSumar);
		btRestar = (Button) findViewById(R.id.btRestar);
		btMultiplicar = (Button) findViewById(R.id.btMultiplicar);
		btDividir = (Button) findViewById(R.id.btDividir);

		btSumar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				operar(TipoOperacion.sum);
			}
		});
		btRestar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				operar(TipoOperacion.subtraction);
			}
		});
		btMultiplicar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				operar(TipoOperacion.multiplication);
			}
		});
		btDividir.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				operar(TipoOperacion.division);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculator, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_return:
			finish();
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Abstraer a nivel de lógica las operaciones para que todas sean
	 * controladas por este método
	 * 
	 * @param to
	 *            TipoOoperacion
	 */
	private void operar(TipoOperacion to) {
		etConsola.setText(null);
		String f = etInput.getText().toString();
		String s = etOutput.getText().toString();
		long result = 0;
		try {
			String fDec = Utils.getDecimal(f, conversorInput);
			String sDec = Utils.getDecimal(s, conversorOutput);
			switch (to) {
			case sum:
				result = (Integer.parseInt(fDec) + Integer.parseInt(sDec));
				break;
			case subtraction:
				result = (Integer.parseInt(fDec) - Integer.parseInt(sDec));
				break;
			case multiplication:
				result = (Integer.parseInt(fDec) * Integer.parseInt(sDec));
				break;
			case division:
				result = (Integer.parseInt(fDec) / Integer.parseInt(sDec));
				break;
			}
			log(getResultsInAllBases(to, result));
		} catch (InvalidFormatException e) {
			log("\n" + e.getMessage());
		} catch (Exception e) {
			log("Exception: " + e.getMessage());
		}
	}

	/**
	 * Obtener el resultado a mostrar en la consola
	 * 
	 * @param result
	 *            String
	 * @return
	 */
	private String getResultsInAllBases(TipoOperacion to, long result) {
		String s = "The result of the " + to.name() + " operation is:";
		s += "\n" + " - In Binary is: " + Long.toString(result, 2);
		s += "\n" + " - In Octal is: " + Long.toString(result, 8);
		s += "\n" + " - In Decimal is: " + Long.toString(result, 10);
		s += "\n" + " - In Hexadecimal is: " + Long.toString(result, 16);
		return s;
	}

}
