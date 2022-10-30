package com.example.listadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.listadetarefas.R;
import com.example.listadetarefas.helper.TarefaDAO;
import com.example.listadetarefas.model.Tarefa;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarTarefa extends AppCompatActivity {
    private TextInputEditText txtTarefa;
    private Tarefa tarefaAtual;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);
        txtTarefa = findViewById(R.id.txtTarefa);
        tarefaAtual= (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");
        if(tarefaAtual!=null){
            txtTarefa.setText(tarefaAtual.getNomeTarefa());
        }


    }
    @Override protected void onStart() {
        super.onStart();
        showKeyboard();
    }
    @Override protected void onStop() {
        super.onStop();
        hidennKeyboard();
    }
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_salvar, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opcao_salvar:
                 TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                if(tarefaAtual!= null){//é uma edição
                    String novoNomeTarefa = txtTarefa.getText().toString();
                    if(txtTarefa.getText().length()!=0){
                        Tarefa tarefa=new Tarefa(novoNomeTarefa, tarefaAtual.getId());
                        /*atualixar a tarefa*/
                        if(tarefaDAO.atualizar(tarefa)){
                            Toast.makeText(getApplicationContext(),
                                    "Tarefa atualizada com sucesso.",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }else{

                            Toast.makeText(getApplicationContext(),
                                    "Ocorreu um erro.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Log.i("INFO ","ERRO: o campo esta vazio");
                        Toast.makeText(getApplicationContext(),
                                "Tarefa não pode estar em branco",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{//é uma adição
                    if(txtTarefa.getText().length()!=0) {
                        Tarefa tarefa = new Tarefa(txtTarefa.getText().toString());

                        if(tarefaDAO.salvar(tarefa)){
                            finish();
                            Log.i("INFO ","Sucesso ao salvar tarefa");
                            Toast.makeText(getApplicationContext(),
                                    "Sucesso ao salvar tarefa",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Log.i("INFO ","ERRO: Não foi possivel salvar a tarefa");
                            Toast.makeText(getApplicationContext(),
                                    "Não foi possivel salvar a tarefa",
                                    Toast.LENGTH_SHORT).show();
                        }


                    }else{
                        Log.i("INFO ","ERRO: o campo esta vazio");
                        Toast.makeText(getApplicationContext(),
                                "Tarefa não pode estar em branco",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void showKeyboard() {
        txtTarefa.requestFocus();
        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_NOT_ALWAYS);
    }//método que exibe o teclado
    public void hidennKeyboard(){
        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(imm.isActive())
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }//metodo que fecha o teclado
}