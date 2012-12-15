package com.um.adivinanumero.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.um.adivinanumero.persistencia.dao.JugadorDAO;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final Integer DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "adivinanumero.db";
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(JugadorDAO.TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		throw new RuntimeException("DB upgrade not supported");
	}

}
