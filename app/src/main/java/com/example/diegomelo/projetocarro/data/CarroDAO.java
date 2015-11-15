package com.example.diegomelo.projetocarro.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.diegomelo.projetocarro.model.Carro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego Melo on 15/11/2015.
 */
public class CarroDAO {

    CarroDbHelper carroDbHelper;

    public CarroDAO(Context context){
        carroDbHelper = new CarroDbHelper(context);
    }

    public void inserir(Carro carro){

        SQLiteDatabase db = carroDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CarroDbHelper.COL_MODELO, carro.modelo);
        values.put(CarroDbHelper.COL_ANO, carro.ano);
        values.put(CarroDbHelper.COL_FABRICANTE, carro.fabricante);
        values.put(CarroDbHelper.COL_MOTOR, carro.motor);
        values.put(CarroDbHelper.COL_IMAGEM, carro.imagem);

        long id = db.insert(CarroDbHelper.NOME_TABELA, null, values);

        if (id == -1){
            throw new RuntimeException("Erro ao inserir o registro no banco de dados.");
        }

        db.close();

    }

    public void excluir(Carro carro){

        SQLiteDatabase db = carroDbHelper.getWritableDatabase();

        db.delete(CarroDbHelper.NOME_TABELA,
                CarroDbHelper.COL_MODELO + " = ? AND " + CarroDbHelper.COL_FABRICANTE + " = ?",
                new String[]{ carro.modelo, carro.fabricante });

        db.close();

    }

    public List<Carro> listar(){

        SQLiteDatabase db = carroDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + CarroDbHelper.NOME_TABELA + " ORDER BY " + CarroDbHelper.COL_MODELO,
                null);

        int idx_modelo = cursor.getColumnIndex(CarroDbHelper.COL_MODELO);
        int idx_ano = cursor.getColumnIndex(CarroDbHelper.COL_ANO);
        int idx_fabricante = cursor.getColumnIndex(CarroDbHelper.COL_FABRICANTE);
        int idx_motor = cursor.getColumnIndex(CarroDbHelper.COL_MOTOR);
        int idx_imagem = cursor.getColumnIndex(CarroDbHelper.COL_IMAGEM);

        List<Carro> carros = new ArrayList<Carro>();

        while (cursor.moveToNext()){
            String modelo = cursor.getString(idx_modelo);
            String ano = cursor.getString(idx_ano);
            String fabricante = cursor.getString(idx_fabricante);
            String motor = cursor.getString(idx_motor);
            String imagem = cursor.getString(idx_imagem);

            Carro carro = new Carro(modelo, fabricante, ano, motor, imagem);
            carros.add(carro);
        }

        cursor.close();
        db.close();

        return carros;
    }

    public boolean isFavorito(Carro carro){
        SQLiteDatabase db = carroDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT _id FROM " + CarroDbHelper.NOME_TABELA + " WHERE " + CarroDbHelper.COL_MODELO + " = ? AND " +
                CarroDbHelper.COL_FABRICANTE + " = ?",
                new String[]{ carro.modelo, carro.fabricante });

        boolean existe = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return existe;
    }
}
