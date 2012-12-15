package com.um.adivinanumero.persistencia.dao;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.um.adivinanumero.dominio.Jugador;
import com.um.adivinanumero.persistencia.DatabaseHelper;

public class JugadorDAO {
	public static final String TABLE_NAME = "jugador";
	
	public static final String KEY_ID = "id";
	public static final String KEY_NOMBRE = "nombre";
	public static final String KEY_CANTIDAD_INTENTOS = "cantidad_intentos";
	
	public static final String TABLE_CREATE =
			"CREATE TABLE " + TABLE_NAME + " (" +
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			KEY_NOMBRE + " TEXT, " +
			KEY_CANTIDAD_INTENTOS + " INTEGER);";

	private DatabaseHelper databaseHelper;

	public JugadorDAO(Context context) {
		databaseHelper = new DatabaseHelper(context);
	}
	
	public Long guardarJugador(Jugador jugador) {
		String insert = "INSERT INTO " + TABLE_NAME +
				"(" + KEY_NOMBRE + ", " + KEY_CANTIDAD_INTENTOS + ") VALUES " +
				"(?, ?);";
		
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		SQLiteStatement stmt = db.compileStatement(insert);
		stmt.bindString(1, jugador.getNombre());
		stmt.bindLong(2, jugador.getCantidadIntentos());
		
		Long id = stmt.executeInsert();
		jugador.setId(id);
		
		return id;
	}
	
	public ArrayList<Jugador> cargarJugadores() {
		String select = "SELECT " + KEY_ID + ", " + KEY_NOMBRE + ", " + KEY_CANTIDAD_INTENTOS + " " +
				"FROM " + TABLE_NAME + " " +
				"ORDER BY " + KEY_CANTIDAD_INTENTOS + " ASC";
		
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		Cursor c = db.rawQuery(select, null);
		
		ArrayList<Jugador> lista = new ArrayList<Jugador>();
		
		if (c.moveToFirst()) {
			do {
				lista.add(new Jugador(
					c.getLong(0),
					c.getString(1),
					c.getInt(2)
				));
			} while (c.moveToNext());
		}
		
		return lista;
	}
	
	public void eliminarJugador(Jugador jugador) {
		String delete = "DELETE FROM " + TABLE_NAME + " " +
				"WHERE id = ?;";
		
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		SQLiteStatement stmt = db.compileStatement(delete);
		stmt.bindLong(1, jugador.getId());
		
		stmt.execute();
	}
}