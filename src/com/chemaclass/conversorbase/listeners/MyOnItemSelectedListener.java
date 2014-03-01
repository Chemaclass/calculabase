package com.chemaclass.conversorbase.listeners;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;

import com.chemaclass.conversorbase.BaseActivity.Conversor;
import com.chemaclass.conversorbase.CalculatorActivity;
import com.chemaclass.conversorbase.base.Base;
import com.chemaclass.conversorbase.base.Binary;
import com.chemaclass.conversorbase.base.Decimal;
import com.chemaclass.conversorbase.base.Hexadecimal;
import com.chemaclass.conversorbase.base.Octal;

public class MyOnItemSelectedListener implements OnItemSelectedListener {
	private Context mContext;
	private Base mBase;
	private Conversor mConversor;
	private LinearLayout mLinearLayout;

	public MyOnItemSelectedListener(Context c, Base b, Conversor co,
			LinearLayout ll) {
		this.mContext = c;
		this.mBase = b;
		this.mConversor = co;
		this.mLinearLayout = ll;
	}

	@Override
	public void onItemSelected(AdapterView<?> adapter, View view, int position,
			long id) {
		// ocultar los botones
		mLinearLayout.setVisibility(LinearLayout.GONE);
		switch (position) {
		case 0: // Binario
			mBase = new Binary();
			mConversor = Conversor.Binary;
			break;
		case 1: // Octal
			mBase = new Octal();
			mConversor = Conversor.Octal;
			break;
		case 2: // Decimal
			mBase = new Decimal();
			mConversor = Conversor.Decimal;
			break;
		case 3: // Hexadecimal
			mBase = new Hexadecimal();
			mConversor = Conversor.Hexadecimal;
			// Si nuestra instancia se corresponde con la calculadora
			if (mContext instanceof CalculatorActivity)
				mLinearLayout.setVisibility(LinearLayout.VISIBLE);
			break;
		}
		// msg("input: " + baseInput.me(), 0);
	}

	@Override
	public void onNothingSelected(AdapterView<?> adapter) {
	}
}