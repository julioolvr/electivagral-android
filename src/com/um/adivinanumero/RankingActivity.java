package com.um.adivinanumero;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.um.adivinanumero.dominio.Jugador;

public class RankingActivity extends FragmentActivity {

	Aplicacion contexto;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.victoria_ranking);

		contexto = (Aplicacion) getApplicationContext();
		
		mostrarRanking();
	}
	
	/*
	 * HELPERs
	 */
	private void mostrarRanking() {
		LinearLayout layout = (LinearLayout) findViewById(R.id.ranking_layout);
		
		for (Jugador j : contexto.getRanking()) {
			TextView jugadorTextView = new TextView(this);
			jugadorTextView.setText(j.getNombre() + " - " + j.getCantidadIntentos() + " intentos");
			jugadorTextView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));

			layout.addView(jugadorTextView); // TODO: Ver NPE cuando un nuevo jugador se suma al ranking
		}
	}
}