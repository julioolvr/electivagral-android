package com.um.adivinanumero.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.um.adivinanumero.R;

public class VictoriaDialog extends DialogFragment {
	/**
	 * Interfaz para que quien lo requiera implemente
	 * listeners para los eventos de este diálogo.
	 * 
	 * @author julio
	 */
	public interface VictoriaDialogListener {
		public void onDialogPositiveClick(DialogFragment dialog);
		public void onDialogCancel(DialogFragment dialog);
	}
	
	static VictoriaDialogListener listener;
	
	/**
	 * Constructor, recibe como parámetro la Activity que implementa los listeners
	 * para los eventos de diálogo.
	 * 
	 * @param activity
	 * 		Activity que implementa {@link VictoriaDialogListener}
	 * @return
	 * 		Nueva instancia del diálogo.
	 */
	public static VictoriaDialog newInstance(Activity activity) {
		try {
			listener = (VictoriaDialogListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " debe implementar VictoriaDialogListener");
		}
		
		VictoriaDialog frag = new VictoriaDialog();
		return frag;
	}
	
	/*
	 * Constructor default privado para forzar el uso del método
	 * newInstance para la generación de instancias.
	 */
	private VictoriaDialog() {}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(getString(R.string.victoria));

		builder.setPositiveButton(getString(R.string.jugar_de_nuevo), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				listener.onDialogPositiveClick(VictoriaDialog.this);
			}
		});
		
		return builder.create();
	}
	
	@Override
	public void onCancel(DialogInterface dialog) {
		listener.onDialogCancel(VictoriaDialog.this);
	}
}