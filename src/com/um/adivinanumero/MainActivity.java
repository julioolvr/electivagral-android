package com.um.adivinanumero;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
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
    		int[] resultado = numero.comparar(guess);
    		
    		TextView[] labelDigitos = new TextView[4];
        	
        	labelDigitos[0] = (TextView) findViewById(R.id.digito1);
        	labelDigitos[1] = (TextView) findViewById(R.id.digito2);
        	labelDigitos[2] = (TextView) findViewById(R.id.digito3);
        	labelDigitos[3] = (TextView) findViewById(R.id.digito4);
        	
        	for (int i = 0; i < guess.length(); i++) {
        		labelDigitos[i].setText(String.valueOf(guess.charAt(i)));
        		labelDigitos[i].setBackgroundColor(coloresEstado(resultado[i]));
        	}
        	
        	guessView.setText("");
        	mostrarMensaje(String.valueOf(numero.acertado(guess)));
    	} catch (IllegalArgumentException e) {
    		mostrarMensaje("Deben ser 4 dígitos");
    	}
    }
    
    private int coloresEstado(int estado) {
    	int color = Color.RED;
    	
    	switch (estado) {
    	case NumeroAleatorio.CORRECTO:
    		color = Color.GREEN;
    		break;
    	case NumeroAleatorio.REGULAR:
    		color = Color.YELLOW;
    		break;
    	case NumeroAleatorio.ERROR:
    		color = Color.RED;
    		break;
    	}
    	
    	return color;
    }
    
    private void mostrarMensaje(String mensaje) {
    	Context context = getApplicationContext();
    	int duration = Toast.LENGTH_SHORT;
    	
    	Toast toast = Toast.makeText(context, mensaje, duration);
    	toast.show();
    }
}
