package com.um.adivinanumero;

import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.um.adivinanumero.dialogs.RankingDialog;
import com.um.adivinanumero.dialogs.RankingDialog.RankingDialogListener;
import com.um.adivinanumero.dialogs.VictoriaDialog;
import com.um.adivinanumero.dialogs.VictoriaDialog.VictoriaDialogListener;
import com.um.adivinanumero.dominio.Intento;
import com.um.adivinanumero.dominio.Jugador;
import com.um.adivinanumero.dominio.NumeroAleatorio;
import com.um.adivinanumero.dominio.Ranking;

public class MainActivity extends FragmentActivity implements
		VictoriaDialogListener,
		RankingDialogListener {

	Aplicacion contexto;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		contexto = (Aplicacion) getApplicationContext();
		mostrarMensaje(contexto.getNumero().toString());
	}

	private void inicializarJuego() {
		contexto.inicializarPartida();

		actualizarCantidades();
		actualizarListaIntentos();
		mostrarMensaje(contexto.getNumero().toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_ranking:
			mostrarRanking();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/*
	 * EVENT LISTENERS
	 */
	public void adivinarNumero(View view) {
		EditText guessView = (EditText) findViewById(R.id.numeroIngresado);
		String guess = guessView.getText().toString();

		try {
			contexto.intentar(guess);

			actualizarCantidades();

			guessView.setText("");

			if (contexto.acertado()) {
				Ranking ranking = Ranking.getRanking(this);
				Integer intentos = contexto.cantidadIntentos();
				
				if (ranking.entraEnRanking(intentos)) {
					DialogFragment newFragment = RankingDialog.newInstance(this);
					newFragment.show(getSupportFragmentManager(), "ranking");
				} else {
					DialogFragment newFragment = VictoriaDialog.newInstance(this);
					newFragment.show(getSupportFragmentManager(), "victoria");
				}
			} else {
				actualizarListaIntentos();
			}
		} catch (IllegalArgumentException e) {
			mostrarMensaje(String.format(getString(R.string.cantidad_digitos), NumeroAleatorio.CANTIDAD_DIGITOS));
		}
	}

	public void onDialogPositiveClick(DialogFragment dialog) {
		// Reinicializar el juego
		inicializarJuego();
	}

	public void onDialogCancel(DialogFragment dialog) {
		// Reinicializar el juego
		inicializarJuego();
	}
	
	@Override
	public void onRankingDialogCancel(DialogFragment dialog) {
		inicializarJuego();
	}
	
	@Override
	public void onRankingDialogPositiveClick(DialogFragment dialog) {
		Ranking ranking = Ranking.getRanking(this);
		Jugador jugador = new Jugador(((RankingDialog)dialog).getNombreJugador(), contexto.cantidadIntentos());
		ranking.agregarAlRanking(jugador, this);
		mostrarRanking();
		inicializarJuego();
	}

	/*
	 * HELPERS
	 */

	private void actualizarCantidades() {
		mostrarCorrectas(contexto.cantidadCorrectas());
		mostrarRegulares(contexto.cantidadRegulares());
		mostrarIncorrectas(contexto.cantidadIncorrectos());

		((TextView) findViewById(R.id.intentos)).setText(contexto
				.cantidadIntentos().toString());
	}

	private void mostrarCorrectas(int cantidad) {
		mostrarResultado(R.id.correctas, cantidad
				+ (cantidad == 1 ? " correcta" : " correctas"));
	}

	private void mostrarRegulares(int cantidad) {
		mostrarResultado(R.id.regulares, cantidad
				+ (cantidad == 1 ? " regular" : " regulares"));
	}

	private void mostrarIncorrectas(int cantidad) {
		mostrarResultado(R.id.incorrectas, cantidad
				+ (cantidad == 1 ? " incorrecta" : " incorrectas"));
	}

	private void mostrarResultado(int viewId, String texto) {
		((TextView) findViewById(viewId)).setText(texto);
	}

	private void mostrarMensaje(String mensaje) {
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, mensaje, duration);
		toast.show();
	}
	
	private void mostrarRanking() {
		Intent irAlRanking = new Intent(getApplicationContext(), RankingActivity.class);
		startActivity(irAlRanking);
	}
	
	private void actualizarListaIntentos() {
		List<Intento> intentos = contexto.getUltimosIntentos(5);
		Collections.reverse(intentos);
		LinearLayout layout = (LinearLayout) findViewById(R.id.intentos_previos_layout);
		
		layout.removeAllViews();
		
		for (Intento i : intentos) {
			TextView intentoTextView = new TextView(this);
			intentoTextView.setText(i.getGuess());
			intentoTextView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
			
			layout.addView(intentoTextView);
		}
	}
}
