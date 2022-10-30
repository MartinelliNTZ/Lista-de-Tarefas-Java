package com.example.listadetarefas.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.listadetarefas.model.ITarefaDAO;
import com.example.listadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO {
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;
//jkljkljk
    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escreve= db.getWritableDatabase();
        le = db.getReadableDatabase();

    }

    @Override public boolean salvar(Tarefa tarefa) {
        try{
            ContentValues cv = new ContentValues();
            cv.put("nome",tarefa.getNomeTarefa());

            escreve.insert(DbHelper.TABELA_TAREFAS,null,cv);
            Log.i("INFO ","Tarefa Salva com Sucesso.");
            return  true;
        }catch (Exception e) {
            Log.i("INFO ","ERRO ao salvar dados. COD: " + e.getMessage());
            return false;
        }
    }
    @Override public boolean atualizar(Tarefa tarefa) {
        try{
            ContentValues cv = new ContentValues();
            cv.put("nome",tarefa.getNomeTarefa());

            String[] args= {tarefa.getId().toString()};
            escreve.update(DbHelper.TABELA_TAREFAS,cv,"id=?",args);
            Log.i("INFO ","Tarefa Salva com Sucesso.");
            return  true;
        }catch (Exception e) {
            Log.i("INFO ","ERRO ao salvar dados. COD: " + e.getMessage());
            return false;
        }
    }
    @Override public boolean deletar(Tarefa tarefa) {

        String[] args= {tarefa.getId().toString()};
        escreve.delete(DbHelper.TABELA_TAREFAS,"id=?",args);
        return false;
    }
    @Override public List<Tarefa> listar() {
         List<Tarefa> tarefas= new ArrayList<>();
        String sql = "SELECT * FROM "+DbHelper.TABELA_TAREFAS+" ;";
        Cursor cursor = le.rawQuery(sql,null);

        while (cursor.moveToNext()){
            Tarefa tarefa=new Tarefa();

            @SuppressLint("Range") Long id = cursor.getLong(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex("nome"));

            tarefa.setNomeTarefa(nome);
            tarefa.setId(id);
            tarefas.add(tarefa);
        }

        return tarefas;
    }
}
