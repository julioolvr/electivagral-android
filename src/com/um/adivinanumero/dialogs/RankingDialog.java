package com.um.adivinanumero.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.um.adivinanumero.R;

public class RankingDialog extends DialogFragment {
	/**
	 * Interfaz para que implementen quien lo requiera implemente
	 * listeners para los eventos de este diálogo.
	 * 
	 * @author julio
	 */
	public interface RankingDialogListener {
		public void onRankingDialogPositiveClick(DialogFragment dialog);
		public void onRankingDialogCancel(DialogFragment dialog);
	}
	
	static RankingDialogListener listener;
	
	/**
	 * Constructor, recibe como parámetro la Activity que implementa los listeners
	 * para los eventos de diálogo.
	 * 
	 * @param activity
	 * 		Activity que implementa {@link RankingDialogListener}
	 * @return
	 * 		Nueva instancia del diálogo.
	 */
	public static RankingDialog newInstance(Activity activity) {
		try {
			listener = (RankingDialogListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " debe implementar RankingDialogListener");
		}
		
		RankingDialog frag = new RankingDialog();
		return frag;
	}
	
	/*
	 * Constructor default privado para forzar el uso del método
	 * newInstance para la generación de instancias.
	 */
	private RankingDialog() {}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		builder.setMessage(getString(R.string.victoria_ranking));
		
		builder.setView(inflater.inflate(R.layout.ranking_dialog, null))
			.setPositiveButton(getString(R.string.aceptar), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					listener.onRankingDialogPositiveClick(RankingDialog.this);
				}
			})
			.setNegativeButton(getString(R.string.jugar_de_nuevo), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					listener.onRankingDialogCancel(RankingDialog.this);
				}
			});
		
		return builder.create();
	}
	
	@Override
	public void onCancel(DialogInterface dialog) {
		listener.onRankingDialogCancel(RankingDialog.this);
	}
	
	public String getNombreJugador() {
		EditText textView = (EditText)this.getDialog().findViewById(R.id.nombre_jugador);
		return textView.getText().toString();
	}
}