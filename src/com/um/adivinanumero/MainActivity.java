package com.um.adivinanumero;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.um.adivinanumero.dominio.NumeroAleatorio;

public class MainActivity extends Activity {
	
	NumeroAleatorio numero;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Elegir número aleatorio
        numero = new NumeroAleatorio();
        TextView label = (TextView) findViewById(R.id.numeroAleatorio);
        label.setText(numero.toString());
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
    	
    	try {
    		SparseIntArray resultado = numero.compararCantidades(guess);
    		
    		mostrarCorrectas(resultado.get(NumeroAleatorio.CORRECTO));
    		mostrarRegulares(resultado.get(NumeroAleatorio.REGULAR));
    		mostrarIncorrectas(resultado.get(NumeroAleatorio.ERROR));
    		
        	guessView.setText("");
        	mostrarMensaje(String.valueOf(numero.acertado(guess)));
    	} catch (IllegalArgumentException e) {
    		mostrarMensaje("Deben ser 4 dígitos");
    	}
    }
    
    /*
     * HELPERS
     */
    
    private void mostrarCorrectas(int cantidad) {
    	mostrarResultado(R.id.correctas, cantidad + (cantidad == 1 ? " correcta" : " correctas"));
    }
    
    private void mostrarRegulares(int cantidad) {
    	mostrarResultado(R.id.regulares, cantidad + (cantidad == 1 ? " regular" : " regulares"));
    }
    
    private void mostrarIncorrectas(int cantidad) {
    	mostrarResultado(R.id.incorrectas, cantidad + (cantidad == 1 ? " incorrecta" : " incorrectas"));
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
