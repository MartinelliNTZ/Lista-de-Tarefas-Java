package com.example.listadetarefas.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.listadetarefas.R;
import com.example.listadetarefas.adapter.AtividadeAdapter;
import com.example.listadetarefas.helper.TarefaDAO;
import com.example.listadetarefas.model.Tarefa;
import com.example.listadetarefas.helper.DbHelper;
import com.example.listadetarefas.helper.RecyclerItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private AtividadeAdapter tarefaAdapter;
    private List<Tarefa> listaTarefas= new ArrayList<>();


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkage();
        setarFAB();
        adicionarOnclickRecyclerView();



    }
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override protected void onStart() {
        setarTarefa();
        super.onStart();
    }

    public void adicionarOnclickRecyclerView(){

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Tarefa tarefaSelecionada = listaTarefas.get(position);
                        Intent intent = new Intent(MainActivity.this, AdicionarTarefa.class);
                        intent.putExtra("tarefaSelecionada", tarefaSelecionada);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        try{Tarefa tarefaSelecionada = listaTarefas.get(position);
                        AlertDialog.Builder aler =new AlertDialog.Builder(MainActivity.this);
                        aler.setMessage("Deseja excluir a tarefa: "+tarefaSelecionada.getNomeTarefa()+"?");
                        aler.setTitle("Excluir itens.");
                        aler.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deletarItens(tarefaSelecionada);
                                Toast.makeText(MainActivity.this, "Deletado.", Toast.LENGTH_SHORT).show();
                            }
                        });
                        aler.setNegativeButton("Não", null);
                        aler.setIcon(R.drawable.ic_delete);
                        aler.create();
                        aler.show();

                        }catch (Exception e){
                            Log.i("INFO","Erro: "+e.getMessage());
                        }

                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                })
        );
    }
    public void setarTarefa(){
        //listar tarefas
            TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
            listaTarefas=tarefaDAO.listar();
        //exibir listas no reciclerview


        // configurar adapter
        tarefaAdapter = new AtividadeAdapter(listaTarefas);
        //configurar o reciclerview
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager( getApplicationContext());
        recyclerView.setAdapter(tarefaAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
    }//configura o recicler view
    public void linkage(){
        recyclerView=findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);
}//find view by id
    public void setarFAB(){

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdicionarTarefa.class);
                startActivity(intent);
            }
        });
    }//método que configura o FAB
    public void deletarItens(Tarefa t){
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        tarefaDAO.deletar(t);
        setarTarefa();
    }





}