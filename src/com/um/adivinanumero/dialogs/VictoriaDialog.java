package com.um.adivinanumero.dialogs;

import com.um.adivinanumero.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class VictoriaDialog extends DialogFragment {
	public interface VictoriaDialogListener {
		public void onDialogPositiveClick(DialogFragment dialog);
		public void onDialogCancel(DialogFragment dialog);
	}
	
	static VictoriaDialogListener listener;
	
	public static VictoriaDialog newInstance(Activity activity) {
		try {
			listener = (VictoriaDialogListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " debe implementar VictoriaDialogListener");
		}
		
		VictoriaDialog frag = new VictoriaDialog();
		return frag;
	}
	
	private VictoriaDialog() {}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(getString(R.string.victoria)); // TODO: Mover como resource

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
