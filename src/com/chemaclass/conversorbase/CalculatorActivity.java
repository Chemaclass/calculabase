package com.chemaclass.conversorbase;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CalculatorActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_calculator);
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {

		/*btConvertir = (Button) findViewById(R.id.btConvertir);
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
		});*/
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

	@Override
	protected void convertir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void invertir() {
		// TODO Auto-generated method stub
		
	}

}
