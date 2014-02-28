package com.chemaclass.conversorbase;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CalculatorActivity extends BaseActivity {

	enum TipoOperacion {
		sumar, restar, multiplicar, dividir;
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
				operar(TipoOperacion.sumar);
			}
		});
		btRestar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				operar(TipoOperacion.restar);
			}
		});
		btMultiplicar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				operar(TipoOperacion.multiplicar);
			}
		});
		btDividir.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				operar(TipoOperacion.dividir);
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
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Abstraer a nivel de lógica las operaciones para que todas sean
	 * controladas por este método
	 * 
	 * @param to TipoOoperacion
	 */
	private void operar(TipoOperacion to) {
		switch (to) {
		case sumar:
			sumar();
			break;
		case restar:
			restar();
			break;
		case multiplicar:
			multiplicar();
			break;
		case dividir:
			dividir();
			break;
		}
	}

	private void sumar() {
		log("Sumar");
		String f = etInput.getText().toString();
		String s = etOutput.getText().toString();

		String fDec = Utils.getConversion(f, baseInput, Conversor.Decimal);
		String sDec = Utils.getConversion(s, baseOutput, Conversor.Decimal);

		String resultado = ""
				+ (Integer.parseInt(fDec) + Integer.parseInt(sDec));

		log("\n" + "La suma es: " + resultado); // consola

	}

	private void restar() {
		log("restar");
	}

	private void multiplicar() {
		log("multiplicar");
	}

	private void dividir() {
		log("dividir");
	}

}
