package com.um.adivinanumero;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.um.adivinanumero.dialogs.VictoriaDialog;
import com.um.adivinanumero.dialogs.VictoriaDialog.VictoriaDialogListener;
import com.um.adivinanumero.dominio.NumeroAleatorio;

public class MainActivity extends Activity implements VictoriaDialogListener {

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

		((TextView) findViewById(R.id.intentos)).setText(contexto.getIntentos()
				.toString());
		
		mostrarCorrectas(0);
		mostrarRegulares(0);
		mostrarIncorrectas(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * EVENT LISTENERS
	 */
	public void adivinarNumero(View view) {
		EditText guessView = (EditText) findViewById(R.id.numeroIngresado);
		String guess = guessView.getText().toString();
		Integer intentos = contexto.incrementarIntentos();

		try {
			SparseIntArray resultado = contexto.getNumero().compararCantidades(
					guess);

			mostrarCorrectas(resultado.get(NumeroAleatorio.CORRECTO));
			mostrarRegulares(resultado.get(NumeroAleatorio.REGULAR));
			mostrarIncorrectas(resultado.get(NumeroAleatorio.ERROR));

			guessView.setText("");

			((TextView) findViewById(R.id.intentos)).setText(intentos
					.toString());

			// TODO: Mover esta lógica a un objeto de dominio
			if (resultado.get(NumeroAleatorio.CORRECTO) == 4) {
				DialogFragment newFragment = VictoriaDialog.newInstance(this);
				get
				newFragment.show(getFragmentManager(), "victoria");
			}
		} catch (IllegalArgumentException e) {
			mostrarMensaje("Deben ser 4 dígitos");
		}
	}

	public void onDialogPositiveClick(DialogFragment dialog) {
		// Reinicializar el juego
		inicializarJuego();
	}

	/*
	 * HELPERS
	 */

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
}
