package com.example.diegomelo.projetocarro.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Diego Melo on 15/11/2015.
 */
public class CarroDbHelper extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "db_carro";
    public static final int VERSAO_BANCO = 1;

    public static final String NOME_TABELA = "carro";

    public static final String COL_ID = "_id";
    public static final String COL_MODELO = "modelo";
    public static final String COL_FABRICANTE = "fabricante";
    public static final String COL_ANO = "ano";
    public static final String COL_MOTOR = "motor";
    public static final String COL_IMAGEM = "imagem";

    public CarroDbHelper(Context context){
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + NOME_TABELA + "(" +
             COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
             COL_MODELO + " TEXT NOT NULL, " +
             COL_FABRICANTE + " TEXT NOT NULL, " +
             COL_ANO + " TEXT NOT NULL, " +
             COL_MOTOR + " TEXT NOT NULL, " +
             COL_IMAGEM + " TEXT NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
