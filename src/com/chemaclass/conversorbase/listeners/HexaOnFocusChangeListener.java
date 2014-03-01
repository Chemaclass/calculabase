package com.chemaclass.conversorbase.listeners;

import android.content.Context;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.chemaclass.conversorbase.base.Base;

/**
 * Construiremos un objeto al que le pasaremos el spinner y el linerlayout. De
 * esta forma, cuando en el spinner seleccionemos algo concreto (en este caso
 * que la base sea HEXA) mostraremos el linearlayout pasado como segundo
 * parámetro. De lo contrario siempre ocultaremos el linearlayout
 * 
 * @author chemaclass
 * 
 */
public class HexaOnFocusChangeListener implements OnFocusChangeListener {
	private Context mContext;
	private Spinner mSpinner;
	private LinearLayout mLinearLayout;

	public HexaOnFocusChangeListener(Context c, Spinner sp, LinearLayout ll) {
		this.mContext = c;
		this.mSpinner = sp;
		this.mLinearLayout = ll;
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// Si tiene el foco y su base es HEXA
		if (hasFocus && mSpinner.getSelectedItemId() == Base.HEXADECIMAL) {
			mLinearLayout.setVisibility(LinearLayout.VISIBLE);
		} else {
			// De lo contrario lo ocultamos
			mLinearLayout.setVisibility(LinearLayout.GONE);
		}
	}
}