package com.chemaclass.calculabase;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutMeActivity extends Activity {

	private TextView tvDescripcion;
	private TextView tvAutor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aboutme);

		tvDescripcion = (TextView) findViewById(R.id.tvDescripcion);
		tvAutor = (TextView) findViewById(R.id.tvAutor);

		String str = getResources().getString(R.string.description);

		tvDescripcion.setText(str);
		tvAutor.setText("@Chemaclass");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.aboutme, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Uri uri;
		Intent browserIntent;
		switch (item.getItemId()) {
		case R.id.action_twitter:
			uri = Uri.parse(Utils.URL_CHEMACLASS);
			browserIntent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(browserIntent);
			return true;
		case R.id.action_share:
			String url_app = Utils.URL_PLAY_CONVERSOR_BASE;
			String text_to_send = "Try 'CalculaBase APP' " + url_app;
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, text_to_send);
			sendIntent.setType("text/plain");
			startActivity(sendIntent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}
}
