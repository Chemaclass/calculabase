package com.chemaclass.conversorbase;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.chemaclass.conversorbase.base.Base;
import com.chemaclass.conversorbase.exceptions.InvalidFormatException;

public class CalculatorActivity extends BaseActivity {

	enum TipoOperacion {
		sum, subtraction, multiplication, division;
	}

	protected Button btSumar, btRestar, btMultiplicar, btDividir;
	protected Button btCleanOutput;
	private TipoOperacion lastTypeOperation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_calculator);
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {
		// Botones de las operaciones
		btSumar = (Button) findViewById(R.id.btSumar);
		btRestar = (Button) findViewById(R.id.btRestar);
		btMultiplicar = (Button) findViewById(R.id.btMultiplicar);
		btDividir = (Button) findViewById(R.id.btDividir);

		btCleanOutput = (Button) findViewById(R.id.btCleanOutput);
		// Añadimos los listener a los botones
		addButtonsListener();
	}

	/**
	 * Añadir los listeners a los botones
	 */
	private void addButtonsListener() {
		btSumar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				preoperar(TipoOperacion.sum);
			}
		});
		btRestar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				preoperar(TipoOperacion.subtraction);
			}
		});
		btMultiplicar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				preoperar(TipoOperacion.multiplication);
			}
		});
		btDividir.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				preoperar(TipoOperacion.division);
			}
		});
		// Borrar el segundo campo de entrada
		btCleanOutput.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				etOutput.setText(null);
			}
		});
	}

	/**
	 * Comprobamos si los datos son válidos
	 * 
	 * @return
	 */
	private boolean youCanDoThis() {
		String input1 = etInput.getText().toString();
		String input2 = etOutput.getText().toString();
		// El el caso de que el primero o el segundo input no se correspondan, o
		// estén vacíos, devolveremos un false
		if (input1.length() == 0 || input2.length() == 0
				|| !check(input1, spInput.getSelectedItemId())
				|| !check(input2, spOutput.getSelectedItemId())) {
			return false;
		}
		return true;
	}

	/**
	 * Verifica que el input esté en la base que se le pide
	 * 
	 * @param input
	 *            String número a verificar
	 * @param base
	 *            Base en la que tiene que estar
	 * @return Si input tuviera algún caracter que su base no puede permitir
	 *         estaría mal formado y por tanto devolvería un false
	 */
	private boolean check(String input, long base) {
		int aux = (int) base;
		switch (aux) {
		case Base.BINARIO:
			if (Utils.isBinary(input))
				return true;
			break;
		case Base.OCTAL:
			if (Utils.isOctal(input))
				return true;
			break;
		case Base.DECIMAL:
			if (Utils.isDecimal(input))
				return true;
			break;
		case Base.HEXADECIMAL:
			if (Utils.isHexadecimal(input))
				return true;
			break;
		}
		return false;
	}

	/**
	 * Antes de realizar la operación tenemos que pasar por unos validadores que
	 * nos aseguren que los datos están bien introducidos
	 * 
	 * @param to
	 *            TipoOperación
	 */
	private void preoperar(TipoOperacion to) {
		if (youCanDoThis()) {
			lastTypeOperation = to; // guardamos la última operación
			operar(lastTypeOperation);
		} else {
			// Mostramos un toast de error
			msg(getResources().getString(R.string.verify), 0);
		}
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
	private String operar(TipoOperacion to) {
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
			String resul = getResultsInAllBases(to, result);
			// Pintamos el resultado
			log(resul);
			// Y lo devolvemos
			return resul;
		} catch (InvalidFormatException e) {
			log("\n" + e.getMessage());
		} catch (Exception e) {
			log("Exception: " + e.getMessage());
		}
		return null;
	}

	/**
	 * Obtener el resultado a mostrar en la consola
	 * 
	 * @param result
	 *            String
	 * @return
	 */
	private String getResultsInAllBases(TipoOperacion to, long result) {
		String s = "The results of the " + to.name() + " operation:";
		s += "\n" + " - In Binary is: " + Long.toString(result, 2);
		s += "\n" + " - In Octal is: " + Long.toString(result, 8);
		s += "\n" + " - In Decimal is: " + Long.toString(result, 10);
		s += "\n" + " - In Hexadecimal is: " + Long.toString(result, 16);
		return s;
	}

	/**
	 * Obtener el resultadoo a mostrar en la consola
	 * 
	 * @return string
	 */
	@Override
	protected String getTextResult() {
		String input1 = etInput.getText().toString();
		String input2 = etOutput.getText().toString();
		String f = input1 + "(" + conversorInput.name() + ")";
		String s = input2 + "(" + conversorOutput.name() + ")";
		String resul = f + s;
		resul += operar(lastTypeOperation);
		return resul;
	}
}
