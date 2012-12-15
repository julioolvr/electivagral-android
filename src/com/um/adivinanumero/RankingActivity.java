package com.um.adivinanumero;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.um.adivinanumero.dominio.Jugador;
import com.um.adivinanumero.dominio.Ranking;

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
		TableLayout layout = (TableLayout) findViewById(R.id.ranking_layout);
		Ranking ranking = Ranking.getRanking(this);
		
		for (Jugador j : ranking) {
			TableRow jugadorTableRow = new TableRow(this);
			
			TextView intentosTextView = new TextView(this);
			intentosTextView.setText(j.getCantidadIntentos().toString());
			intentosTextView.setLayoutParams(new TableRow.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
			
			TextView nombreTextView = new TextView(this);
			nombreTextView.setText(j.getNombre());
			nombreTextView.setLayoutParams(new TableRow.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));

			jugadorTableRow.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
			jugadorTableRow.addView(intentosTextView);
			jugadorTableRow.addView(nombreTextView);
			
			layout.addView(jugadorTableRow);
		}
	}
}