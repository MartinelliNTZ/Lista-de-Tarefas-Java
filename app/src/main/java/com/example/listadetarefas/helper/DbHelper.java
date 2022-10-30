package com.example.listadetarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.listadetarefas.activity.MainActivity;
import com.example.listadetarefas.model.Tarefa;

public class DbHelper extends SQLiteOpenHelper {
    public static int VERSION= 1;
    public static String NOME_DB= "DB_TAREFAS";
    public static String TABELA_TAREFAS= "tarefas";
    private Context contextAplication;



    public DbHelper(@Nullable Context context ) {
        super(context, NOME_DB, null, VERSION);
        contextAplication=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " +
                TABELA_TAREFAS+
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL); ";

        try {
            sqLiteDatabase.execSQL(sql);
            Log.i("INFO DB","Sucesso ao criar tabela:  "+TABELA_TAREFAS);
        }catch(Exception e){
            Log.i("INFO DB","ERRO ao criar tabela:  "+ TABELA_TAREFAS+" "+e.getMessage());
            }




    }//Método chamado uma unica vez na criacao do app

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }//método ultilizado na atualizacao dos dados
}
